package priv.eric.mini.engine.kit;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Description: fetch value in object by express
 * <ul>
 *     <li>
 *         ex. there has an object named ericObj {@code {"name": "eric", "age": 16}}, get name field value by express ericObj.name
 *     </li>
 *     <li>
 *         ex. there has en object named ericCol {@code {"name": ["eric", "ec"], "age": 16}}, get first name value by express ericCol.name[0]
 *     </li>
 * </ul>
 *
 * @author EricTowns
 * @date 2023/5/6 15:58
 */
public final class Extractor {

    private final String express;

    private final Object originValue;

    public Extractor(Object originValue, String express) {
        this.originValue = originValue;
        this.express = express;
    }

    public static Extractor of(Object originValue, String express) {
        return new Extractor(originValue, express);
    }

    public Object getValue() {
        Tokenizer tokenizer = new Tokenizer(this.express);
        return getValue(tokenizer, originValue);
    }

    private Object getValue(Tokenizer tokenizer, Object value) {
        if (tokenizer.invalid()) {
            return null;
        }

        Object processValue = getBeanValue(tokenizer, value);
        if (tokenizer.asCollection()) {
            processValue = getCollectionValue(tokenizer, processValue);
        }

        while (tokenizer.hasNext() && processValue != null) {
            Tokenizer nextTokenizer = tokenizer.next();
            getValue(nextTokenizer, processValue);
        }
        return processValue;
    }

    private Object getBeanValue(Tokenizer tokenizer, Object beanValue) {
        String name = tokenizer.getName();
        if (beanValue instanceof Collection) {
            Collection<Object> beanValueCollection = (Collection<Object>) beanValue;
            Collection<Object> beanValues = new ArrayList<>(beanValueCollection.size());
            for (Object value : beanValueCollection) {
                Object beanValueObj = getBeanValue(name, value);
                beanValues.add(beanValueObj);
            }
            return beanValues;
        }
        return getBeanValue(name, beanValue);
    }

    private Object getBeanValue(String name, Object beanValue) {
        try {
            if (beanValue instanceof Map<?, ?>) {
                Map<?, ?> beanValueMap = (Map<?, ?>) beanValue;
                return beanValueMap.get(name);
            } else {
                Field field = beanValue.getClass().getField(name);
                field.setAccessible(true);
                return field.get(beanValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    private Object getCollectionValue(Tokenizer property, Object collectionObject) {
        if (collectionObject instanceof Map) {
            return ((Map<?, ?>) collectionObject).get(property.getIndex());
        } else {
            int index = Integer.parseInt(property.getIndex());
            if (collectionObject instanceof List) {
                return ((List<?>) collectionObject).get(index);
            } else if (collectionObject instanceof Object[]) {
                return ((Object[]) collectionObject)[index];
            } else if (collectionObject instanceof char[]) {
                return ((char[]) collectionObject)[index];
            } else if (collectionObject instanceof boolean[]) {
                return ((boolean[]) collectionObject)[index];
            } else if (collectionObject instanceof int[]) {
                return ((int[]) collectionObject)[index];
            } else if (collectionObject instanceof float[]) {
                return ((float[]) collectionObject)[index];
            } else if (collectionObject instanceof double[]) {
                return ((double[]) collectionObject)[index];
            } else if (collectionObject instanceof short[]) {
                return ((short[]) collectionObject)[index];
            } else if (collectionObject instanceof byte[]) {
                return ((byte[]) collectionObject)[index];
            } else if (collectionObject instanceof long[]) {
                return ((long[]) collectionObject)[index];
            } else {
                throw new IllegalArgumentException("The '" + property.getName() + "' property of " + collectionObject + " is not a List or Array.");
            }
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

        public boolean invalid() {
            return this.name == null || this.name.isEmpty();
        }

        public boolean asCollection() {
            return this.index != null && !this.index.isEmpty();
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
