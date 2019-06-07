
package hu.webtown.liferay.portlet.reference.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceRealizationDateComparator
    extends CustomOrderByComparator<Reference> {

    private static final long serialVersionUID = 7634908658449658162L;

    public static final String ORDER_BY_ASC =
        "Reference.realizationDate ASC, Reference.version ASC";

    public static final String ORDER_BY_DESC =
        "Reference.realizationDate DESC, Reference.version DESC";

    private static final String[] ORDER_BY_FIELDS = {
        "realizationDate", "version"
    };

    public ReferenceRealizationDateComparator() {

        this(false);
    }

    public ReferenceRealizationDateComparator(boolean ascending) {

        super(ORDER_BY_ASC, ORDER_BY_DESC, ORDER_BY_FIELDS, ascending);
    }

    @Override
    public int doCompare(Reference o1, Reference o2) {

        int value = DateUtil.compareTo(
            o1.getRealizationDate(), o2.getRealizationDate());

        if (value == 0) {

            if (o1.getVersion() < o2.getVersion()) {

                return -1;
            } else if (o1.getVersion() > o2.getVersion()) {

                return 1;
            }
        }

        return value;
    }

}
