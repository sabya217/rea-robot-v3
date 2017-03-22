package com.reagroup.exercises.toyrobot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reagroup.exercises.toyrobot.executor.Reporter;
import com.reagroup.exercises.toyrobot.executor.capturer.Capturer;
import com.reagroup.exercises.toyrobot.executor.capturer.OutputFileCapturer;
import com.reagroup.exercises.toyrobot.input.FileInputSource;
import com.reagroup.exercises.toyrobot.input.InputSource;
import com.reagroup.exercises.toyrobot.position.Surface;
import com.reagroup.exercises.toyrobot.robot.IRobot;
import com.reagroup.exercises.toyrobot.robot.Robot;

/**
 * Entry point for Rea-robot toy simulator.
 */
public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
    public static void main(final String[] args) {
    	LOG.trace("About to start the toy robot simulator.");
    	
    	final Args input = Args.parse(args);
    	if(!input.isValid()) {
    		LOG.trace("The arguments for the toy robot simulator is invalidated ! Exiting !");
    		return;
    	}
    	LOG.trace("Arguments are successfully validated.");
    	
    	final InputSource inputSource = FileInputSource.from(input.inputPath);
    	LOG.trace("Created input source from the file name.");
    	
    	final Surface tableTop = Surface.REA_ROBOT_TABLE_TOP;
    	LOG.info("Using the default \"{}\" as the surface boundary.", tableTop);
    	
    	final IRobot robot = Robot.create();
    	LOG.trace("Created the robot.");
    	
    	setOutputFile(input.outputPath);
    	
    	LOG.trace("About to execute commands..");
    	inputSource.getCommandStream().forEach(command -> robot.execute(command, tableTop));
    	
    	LOG.trace("Finalizing the reporters.");
    	Reporter.instance().finish();
    }
    
    private static void setOutputFile(Path outputPath) {
		if(outputPath == null) {
			return;
		}
		
		final Capturer<String> fileCapturer = OutputFileCapturer.create(outputPath);
		Reporter.instance().setCapturer(fileCapturer);
		LOG.trace("The output file capturer is set.");
	}

    /**
     * Processes the input arguments and performs any necessary pre-execution steps.
     * 
     * @author Sabya
     *
     */
	private static class Args {
    	
    	private static final String INPUT_OPTION = "-input";
    	
    	private static final String OUTPUT_OPTION = "-output";
    	
    	private final Path inputPath;
    	
    	private final Path outputPath;
    	
    	private Args(){
    		this(null, null);
    	}

		/**
		 * @param inputPath
		 * @param outputPath
		 */
		private Args(Path inputPath, Path outputPath) {
			this.inputPath = inputPath;
			this.outputPath = outputPath;
		}

		public boolean isValid() {
			return this.inputPath != null;
		}
		
		/**
		 * Creates the necessary arguments for toy robot simulation parsing the input arguments.
		 * 
		 * @param args
		 * @return an instance of {@link Args}
		 */
		public static Args parse(final String[] args) {
    		if(args.length == 0) {
    			syntax();
    			return new Args();
    		}
    		
    		if(args.length != 2 && args.length != 4) {
    			System.out.println("Invalid arguments !!");
    			syntax();
    			return new Args();
    		}
    		
    		String inputFile = null;
    		String outputFile = null;
    		for(int i=0; i < args.length; i++) {
    			if(args[i].equalsIgnoreCase(INPUT_OPTION)) {
    				if(i == args.length - 1) {
    					System.out.println("Invalid arguments !!");
    	    			syntax();
    	    			return new Args();
    				}
    				inputFile = args[i+1];
    			}
    			if(args[i].equalsIgnoreCase(OUTPUT_OPTION)) {
    				if(i == args.length - 1) {
    					System.out.println("Invalid arguments !!");
    	    			syntax();
    	    			return new Args();
    				}
    				outputFile = args[i+1];
    			}
    		}
    		final Args input = validateAndCreate(inputFile, outputFile);
    		
    		return input;
    	}
    	
    	private static Args validateAndCreate(final String inputFile, final String outputFile) {
    		Path inputPath = null;
    		Path outputPath = null;
    		
    		if(inputFile == null) {
    			System.out.println("Invalid arguments !!");
    			syntax();
    			return new Args();
    		}
    		inputPath = Paths.get(inputFile);
			if(!Files.exists(inputPath)) {
				System.out.println("Input file does not exist !! Exiting !!");
    			return new Args();
			}
			if(Files.isDirectory(inputPath)) {
				System.out.println("Input file name specified is a directory !! Exiting !!");
    			return new Args();
			}
			if(!Files.isReadable(inputPath)) {
				System.out.println("Input file is not readable !! Exiting !!");
    			return new Args();
			}
			if(outputFile != null) {
				outputPath = Paths.get(outputFile);
				if(Files.exists(outputPath)) {
					if(Files.isDirectory(outputPath)) {
						System.out.println("Output file is a directory !! Exiting !!");
						return new Args();
					}
					if(!Files.isWritable(outputPath)) {
						System.out.println("Output file is not writable !! Exiting !!");
						return new Args();
					}
				} else {
					Path parent = outputPath.getParent();
					try {
						Files.createDirectories(parent);
					} catch(Exception e) {
						System.out.println("Error creating the output file, possible permission issue !! Exiting !!");
						return new Args();
					}
				}
			}
			return new Args(inputPath, outputPath);
		}

		private static void syntax() {
    		System.out.println("Please run the toy robot simulator as follows.");
    		System.out.println("\n\tjava -jar rea-robot-v3.jar -input <inputFileName> [-output <outputFileName>]\n");
    		System.out.println("Each line in the input file should respond to an acceptable command.");
    		System.out.println("Output file option is optional, it helps save the output report to a file. "
    				+ "If the output file does not exist, it will be created, else, its contents will be replaced with the new contents.");
    	}
    }
}
