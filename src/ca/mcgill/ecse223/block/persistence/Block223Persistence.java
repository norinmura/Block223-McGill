package ca.mcgill.ecse223.block.persistence;

import ca.mcgill.ecse223.block.model.Block223;

public class Block223Persistence {
  private static String fileName = "data.txt";

  public static void setFileName(String fileName) {
    Block223Persistence.fileName = fileName;
  }

  public static void save(Block223 block223) {
    PersistenceObjectStream.setFileName(fileName);
    PersistenceObjectStream.serialize(block223);
  }

  public static Block223 load() {
    PersistenceObjectStream.setFileName(fileName);
    Block223 block223 = (Block223) PersistenceObjectStream.deserialize();
    if (block223 == null) {
      block223 = new Block223();
    } else {
      block223.reinitialize();
    }
    return block223;
  }
}
