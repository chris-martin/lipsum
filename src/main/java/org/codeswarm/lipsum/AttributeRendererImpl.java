package org.codeswarm.lipsum;

import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;

class AttributeRendererImpl implements Lipsum.AttributeRenderer {

  private final Map<String, Lipsum.ParagraphGenerator> registeredLipsums =
    Maps.newHashMapWithExpectedSize(4);

  @Override
  public void register(String format, Lipsum.ParagraphGenerator paragraphGenerator) {
    registeredLipsums.put(format, paragraphGenerator);
  }

  @Override
  public String toString(Object o, String formatString, Locale locale) {
    if (registeredLipsums.containsKey(formatString)) {
      Lipsum.ParagraphGenerator paragraphGenerator = registeredLipsums.get(formatString);
      Long key = null;
      if (o instanceof Integer) {
        key = ((Integer) o).longValue();
      } else if (o instanceof Long) {
        key = (Long) o;
      }
      if (key != null) {
        return paragraphGenerator.paragraph(key);
      }
    }
    return o.toString();
  }

}
