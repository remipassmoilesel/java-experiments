package org.prog2.files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadPaths {

	public static void main(String[] args) throws IOException {

		Path p = Paths.get("/path/to/image2.png");

		System.out.println(p.getNameCount());
		System.out.println(p.getFileName());
		System.out.println(p.getFileSystem());
		System.out.println(p.getRoot());
		System.out.println(p.getParent());

		System.out.println(p.toUri());

		System.out.println(p.startsWith("/path"));

		System.out.println(p.resolve("hello"));

		Iterable<Path> list = FileSystems.getDefault().getRootDirectories();
		for (Path pt : list) {
			System.out.println(pt);
		}

		DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("/"));
		for (Path p2 : stream) {
			System.out.println(p2);
		}

	}
}
