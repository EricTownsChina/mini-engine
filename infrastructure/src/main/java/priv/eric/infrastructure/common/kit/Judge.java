package priv.eric.infrastructure.common.kit;

import java.util.*;
import java.util.function.BiPredicate;

/**
 * Description: judge condition is true or false
 *
 * @author EricTowns
 * @date 2023/5/16 15:52
 */
public final class Judge {

    public static boolean judge(Condition condition) {
        Comparison comparison = condition.getComparison();
        BiPredicate<Object, List<Object>> predicate = comparison.predicate;
        return predicate.test(condition.getVar1(), condition.getVar2());
    }

    public static class Condition {
        private Object var1;
        private Comparison comparison;
        private List<Object> var2;

        public Object getVar1() {
            return var1;
        }

        public void setVar1(Object var1) {
            this.var1 = var1;
        }

        public Comparison getComparison() {
            return comparison;
        }

        public void setComparison(Comparison comparison) {
            this.comparison = comparison;
        }

        public List<Object> getVar2() {
            return var2;
        }

        public void setVar2(List<Object> var2) {
            this.var2 = var2;
        }
    }

    private static <T> Integer compare(T var1, T var2) {
        return compare(var1, var2, false);
    }

    private static <T> Integer compare(T var1, T var2, boolean nullsFirst) {
        if (var1 == null && var2 == null) {
            return 0;
        } else if (var1 == null && nullsFirst) {
            return 1;
        } else if (var1 == null) {
            return -1;
        } else {
            if (var1 instanceof Comparable) {
                return ((Comparable<T>) var1).compareTo(var2);
            } else {
                return var1.toString().compareTo(var2.toString());
            }
        }
    }

    public enum Comparison {
        EQUALS((o1, o2) -> Judge.compare(o1, o2.get(0)).equals(0)),
        GT((o1, o2) -> Judge.compare(o1, o2.get(0)) > 0),
        GTE((o1, o2) -> Judge.compare(o1, o2.get(0)) >= 0),
        LT((o1, o2) -> Judge.compare(o1, o2.get(0)) < 0),
        LTE((o1, o2) -> Judge.compare(o1, o2.get(0)) <= 0),
        IS_TRUE((o1, o2) -> (Boolean.TRUE.equals(o1))),
        IS_FALSE((o1, o2) -> (Boolean.FALSE.equals(o1))),
        IN((o1, o2) -> o2.contains(o1)),
        EMPTY((o1, o2) -> {
            if (Objects.isNull(o1)) {
                return true;
            } if (o1 instanceof Collection) {
                return ((Collection<?>) o1).isEmpty();
            } else if (o1 instanceof Map) {
                return ((Map<?, ?>) o1).isEmpty();
            } else if (o1 instanceof String) {
                return o1.toString().isEmpty();
            } else  {
                return false;
            }
        }),
        NOT_EMPTY((o1, o2) -> {
            if (Objects.isNull(o1)) {
                return false;
            } if (o1 instanceof Collection) {
                return !((Collection<?>) o1).isEmpty();
            } else if (o1 instanceof Map) {
                return !((Map<?, ?>) o1).isEmpty();
            } else if (o1 instanceof String) {
                return !o1.toString().isEmpty();
            } else  {
                return false;
            }
        });

        final BiPredicate<Object, List<Object>> predicate;

        Comparison(BiPredicate<Object, List<Object>> predicate) {
            this.predicate = predicate;
        }
    }

}
