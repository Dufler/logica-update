package it.ltc.logica.update;

import java.io.File;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

public class UpdateProcedure {

	private static final String localPath = "C:/Users/Damiano/workspace-rcp/Update/";
	
	private static final String rootFile = "site.xml";
	private static final String artifactFile = "artifacts.jar";
	private static final String contentFile = "content.jar";
	private static final String indexFile = "index.html";
	private static final String featuresFolderPath = "features/";
	private static final String pluginsFolderPath = "plugins/";
	
	private static final String ftpHost = "test.services.ltc-logistics.it";
	private static final int ftpPort = 21;
	
	private static final String ftpUser = "logica-update";
	private static final String ftpPassword = "update";
	
	//private static FTPUtility ftp;
	private static FTPClient client;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Procedura avviata!");
		//ftp = new FTPUtility(ftpHost, ftpUser, ftpPassword);
		client = new FTPClient();
		client.connect(ftpHost, ftpPort);
		client.login(ftpUser, ftpPassword);
		//Elimino il file radice
		client.deleteFile(rootFile);
		//boolean deleteRoot = ftp.deleteFile(rootFile);
		//if (!deleteRoot)
		//	System.out.println("(Warning) non è stato eliminato il file radice.");
		//Elimino il file artifact
		client.deleteFile(artifactFile);
		//boolean deleteArtifact = ftp.deleteFile(artifactFile);
		//if (!deleteArtifact)
		//	System.out.println("(Warning) non è stato eliminato il file artifact.");
		//Elimino il file content
		client.deleteFile(contentFile);
		//boolean deleteContent = ftp.deleteFile(contentFile);
		//if (!deleteContent)
		//	System.out.println("(Warning) non è stato eliminato il file content.");
		//Elimino il file index
		client.deleteFile(indexFile);
		//boolean deleteIndex = ftp.deleteFile(indexFile);
		//if (!deleteIndex)
		//	System.out.println("(Warning) non è stato eliminato il file index.");
		//Elimino le vecchie features
		client.changeDirectory(featuresFolderPath);
		FTPFile[] features = client.list();
		for (FTPFile feature : features)
			client.deleteFile(feature.getName());
		//List<String> features = ftp.getFiles(featuresFolderPath);
		//int featuresEliminate = eliminaFiles(featuresFolderPath, features);
		//if (featuresEliminate != features.size())
		//	System.out.println("(Warning) differenza nel numero di features eliminate. " + featuresEliminate + " contro " + features.size());
		//Elimino i vecchi plugins
		client.changeDirectoryUp();
		client.changeDirectory(pluginsFolderPath);
		FTPFile[] plugins = client.list();
		for (FTPFile plugin : plugins)
			client.deleteFile(plugin.getName());
		//List<String> plugins = ftp.getFiles(pluginsFolderPath);
		//int pluginsEliminati = eliminaFiles(pluginsFolderPath, plugins);
		//if (pluginsEliminati != plugins.size())
		//	System.out.println("(Warning) differenza nel numero di plugins eliminati. " + pluginsEliminati + " contro " + plugins.size());
		//Eseguo l'upload dei files
		client.changeDirectoryUp();
		client.upload(new File(localPath + rootFile));
		client.upload(new File(localPath + artifactFile));
		client.upload(new File(localPath + contentFile));
		client.upload(new File(localPath + indexFile));
		client.changeDirectory(featuresFolderPath);
		File featuresFolder = new File(localPath + featuresFolderPath);
		for (String feature : featuresFolder.list()) {
			client.upload(new File(localPath + featuresFolderPath + feature));
		}
		client.changeDirectoryUp();
		client.changeDirectory(pluginsFolderPath);
		File pluginsFolder = new File(localPath + pluginsFolderPath);
		for (String plugin : pluginsFolder.list()) {
			client.upload(new File(localPath + pluginsFolderPath + plugin));
		}
		client.disconnect(true);
//		boolean uploadRoot = ftp.upload(localPath + rootFile, rootFile);
//		if (!uploadRoot)
//			System.out.println("(Error) non è stato caricato il file radice!");
//		boolean uploadArtifact = ftp.upload(localPath + artifactFile, artifactFile);
//		if (!uploadArtifact)
//			System.out.println("(Error) non è stato caricato il file artifact!");
//		boolean uploadContent = ftp.upload(localPath + contentFile, contentFile);
//		if (!uploadContent)
//			System.out.println("(Error) non è stato caricato il file content!");
//		boolean uploadIndex = ftp.upload(localPath + indexFile, indexFile);
//		if (!uploadIndex)
//			System.out.println("(Error) non è stato caricato il file index!");
//		File featuresFolder = new File(localPath + featuresFolderPath);
//		int featuresCaricate = caricaFiles(featuresFolderPath, featuresFolder.list());
//		System.out.println("Sono state caricate " + featuresCaricate + " features!");
//		File pluginsFolder = new File(localPath + pluginsFolderPath);
//		int pluginsCaricati = caricaFiles(pluginsFolderPath, pluginsFolder.list());
//		System.out.println("Sono state caricati " + pluginsCaricati + " plugins!");
		System.out.println("Procedura terminata!");
	}
	
//	private static int eliminaFiles(String folder, List<String> files) {
//		int fileEliminati = 0;
//		for (String file : files) {
//			boolean delete = ftp.deleteFile(folder + file);
//			if (delete)
//				fileEliminati += 1;
//		}
//		return fileEliminati;
//	}
//	
//	private static int caricaFiles(String folder, String[] files) {
//		int fileCaricati = 0;
//		for (String file : files) {
//			boolean upload = ftp.upload(localPath + folder + file, folder + file);
//			if (upload)
//				fileCaricati += 1;
//		}
//		return fileCaricati;
//	}

}
