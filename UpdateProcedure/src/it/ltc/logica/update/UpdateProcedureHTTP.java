package it.ltc.logica.update;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Copia ricorsivamente il contenuto della cartella del progetto Update nel progetto web, pronto per essere caricato sul server.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class UpdateProcedureHTTP extends SimpleFileVisitor<Path> {
	
	private static final String localPath = "C:/Users/Damiano/git-logica/Update/";
	private static final String buildPath = "C:/Users/Damiano/workspace-jee/it.ltc.logica.update/WebContent/";
	
	private Path sourceDir;
    private Path targetDir;
 
    public UpdateProcedureHTTP(Path sourceDir, Path targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

	public static void main(String[] args) throws Exception {
		System.out.println("Procedura avviata.");
		Path source = Paths.get(new File(localPath).toURI());
		Path target = Paths.get(new File(buildPath).toURI());
		Files.walkFileTree(source, new UpdateProcedureHTTP(source, target));
		System.out.println("Procedura completata.");
	}
	
	@Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
 
        try {
            Path targetFile = targetDir.resolve(sourceDir.relativize(file));
            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        return FileVisitResult.CONTINUE;
    }
 
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
        try {
            Path newDir = targetDir.resolve(sourceDir.relativize(dir));
            File directory = newDir.toFile();
            //Svuoto le cartelle features e plugins prima di copiarci dentro i files.
            if (directory.exists() && (directory.getName().equals("features") || directory.getName().equals("plugins"))) {
            	deleteDirectory(directory);
            }
            Files.createDirectory(newDir);
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        return FileVisitResult.CONTINUE;
    }
    
    private void deleteDirectory(File directory) {
    	boolean directoryClear = true;
    	for (File file : directory.listFiles()) {
    		boolean deleteFile = file.delete();
    		if (!deleteFile) {
    			directoryClear = false;
    			System.out.println("Impossibile eliminare: '" + file.getName() + "'");
    		}
    	}
    	if (directoryClear) {
    		boolean deleteFolder = directory.delete();
        	if (!deleteFolder)
        		System.out.println("Impossibile eliminare la cartella: '" + directory.getName() + "'");
    	} else {
    		System.out.println("Impossibile svuotare la cartella: " + directory.getName() + "'");
    	}
    }

}
