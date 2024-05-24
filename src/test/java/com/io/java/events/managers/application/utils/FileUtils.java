package com.io.java.events.managers.application.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

public class FileUtils {

  private FileUtils() {
  }

  public static String readFileFromClassLoader(String name) {
    try {
      File file =
              new File(
                      Objects.requireNonNull(
                                      Thread.currentThread().getContextClassLoader().getResource(name))
                              .toURI());
      return Files.readString(file.toPath());
    } catch (Exception ex) {
      throw new RuntimeException("Error reading file [" + name + "]", ex);
    }
  }

  public static boolean hasFields(String json, String... fields) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(json);
      for (String field : fields) {
        if (!jsonNode.has(field)) {
          return false;
        }
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static String removeField(String json, String fieldToRemove) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(json);
      jsonNode.remove(fieldToRemove);
      return objectMapper.writeValueAsString(jsonNode);
    } catch (Exception e) {
      return null;
    }
  }

}
