package org.codeswarm.lipsum2;

import java.util.List;

class ListParagraphGenerator implements Lipsum.ParagraphGenerator {

    private final List<?> list;

    public ListParagraphGenerator(List<?> list) {
        this.list = list;
    }

    @Override
    public String paragraph(long key) {
        return list.get(Lipsum.remainder(key, list.size())).toString();
    }

}
