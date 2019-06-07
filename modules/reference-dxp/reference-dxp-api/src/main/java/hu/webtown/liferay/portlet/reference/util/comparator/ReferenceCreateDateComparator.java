
package hu.webtown.liferay.portlet.reference.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceCreateDateComparator
    extends CustomOrderByComparator<Reference> {

    private static final long serialVersionUID = -3582187027085539594L;

    public static final String ORDER_BY_ASC = "Reference.createDate ASC";

    public static final String ORDER_BY_DESC = "Reference.createDate DESC";

    private static final String[] ORDER_BY_FIELDS = {
        "createDate"
    };

    public ReferenceCreateDateComparator() {

        this(false);
    }

    public ReferenceCreateDateComparator(boolean ascending) {

        super(ORDER_BY_ASC, ORDER_BY_DESC, ORDER_BY_FIELDS, ascending);
    }

    @Override
    public int doCompare(Reference o1, Reference o2) {

        return DateUtil.compareTo(o1.getCreateDate(), o2.getCreateDate());
    }

}
