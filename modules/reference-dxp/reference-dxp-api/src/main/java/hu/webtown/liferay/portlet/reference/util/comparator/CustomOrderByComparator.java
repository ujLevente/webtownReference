
package hu.webtown.liferay.portlet.reference.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;

public abstract class CustomOrderByComparator<T> extends OrderByComparator<T> {

    private static final long serialVersionUID = -4711183004376581385L;

    private String orderByAsc;
    private String orderByDesc;
    private String[] orderByFields;
    private boolean ascending;

    public CustomOrderByComparator(
        final String orderByAsc, final String orderByDesc,
        final String[] orderByFields) {

        this(orderByAsc, orderByDesc, orderByFields, false);
    }

    public CustomOrderByComparator(
        final String orderByAsc, final String orderByDesc,
        final String[] orderByFields, final boolean ascending) {

        super();

        this.orderByAsc = orderByAsc;
        this.orderByDesc = orderByDesc;
        this.orderByFields = orderByFields;
        this.ascending = ascending;
    }

    @Override
    public int compare(T o1, T o2) {

        int value = doCompare(o1, o2);

        if (ascending) {

            return value;
        } else {

            return -value;
        }
    }

    public abstract int doCompare(T o1, T o2);

    @Override
    public String getOrderBy() {

        if (ascending) {

            return orderByAsc;
        } else {

            return orderByDesc;
        }
    }

    public String getOrderByAsc() {

        return this.orderByAsc;
    }

    public String getOrderByDesc() {

        return this.orderByDesc;
    }

    @Override
    public String[] getOrderByFields() {

        return orderByFields;
    }

    @Override
    public boolean isAscending() {

        return ascending;
    }

}
