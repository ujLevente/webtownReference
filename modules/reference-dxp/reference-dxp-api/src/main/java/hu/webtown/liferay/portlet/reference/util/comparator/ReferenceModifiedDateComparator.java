
package hu.webtown.liferay.portlet.reference.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceModifiedDateComparator
    extends CustomOrderByComparator<Reference> {

    private static final long serialVersionUID = -6801282163697758741L;

    public static final String ORDER_BY_ASC = "Reference.modifiedDate ASC";

    public static final String ORDER_BY_DESC = "Reference.modifiedDate DESC";

    private static final String[] ORDER_BY_FIELDS = {
        "modifiedDate"
    };

    public ReferenceModifiedDateComparator() {

        this(false);
    }

    public ReferenceModifiedDateComparator(boolean ascending) {

        super(ORDER_BY_ASC, ORDER_BY_DESC, ORDER_BY_FIELDS, ascending);
    }

    @Override
    public int doCompare(Reference o1, Reference o2) {

        return DateUtil.compareTo(o1.getModifiedDate(), o2.getModifiedDate());
    }

}
