package priv.eric.mini.engine.kit;

import java.util.Iterator;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/6 15:58
 */
public class Extractor {

    private String express;

    private Object originValue;

    public Extractor(Object originValue, String express) {
        this.originValue = originValue;
        this.express = express;
    }

    public Object getValue() {
        return null;
    }

    private Object getValue(String express) {
        Tokenizer tokenizer = new Tokenizer(express);
        while (tokenizer.hasNext()) {
            Tokenizer nextTokenizer = tokenizer.next();

        }
    }

    private Object getValue(Tokenizer tokenizer) {
        if (tokenizer.getIndex() != null) {

        }
    }

    private static class Tokenizer implements Iterator<Tokenizer> {
        private final String indexName;
        private final String children;
        private String name;
        private String index;

        public Tokenizer(String fullName) {
            int delimiter = fullName.indexOf(".");
            if (delimiter > -1) {
                name = fullName.substring(0, delimiter);
                children = fullName.substring(delimiter + 1);
            } else {
                name = fullName;
                children = null;
            }
            indexName = name;
            delimiter = name.indexOf("[");
            if (delimiter > -1) {
                index = name.substring(delimiter + 1, name.length() - 1);
                name = name.substring(0, delimiter);
            }
        }

        public String getName() {
            return name;
        }

        public String getIndexName() {
            return indexName;
        }

        public String getIndex() {
            return index;
        }

        public String getChildren() {
            return children;
        }

        @Override
        public boolean hasNext() {
            return children != null;
        }

        @Override
        public Tokenizer next() {
            return new Tokenizer(children);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported, it is no meaning.");
        }
    }

}
