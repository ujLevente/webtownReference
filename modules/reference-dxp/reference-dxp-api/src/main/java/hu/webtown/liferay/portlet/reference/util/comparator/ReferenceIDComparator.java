
package hu.webtown.liferay.portlet.reference.util.comparator;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceIDComparator extends CustomOrderByComparator<Reference> {

    private static final long serialVersionUID = 5681969512552549947L;

    public static final String ORDER_BY_ASC =
        "Reference.referenceId ASC, Reference.version ASC";

    public static final String ORDER_BY_DESC =
        "Reference.referenceId DESC, Reference.version DESC";

    private static final String[] ORDER_BY_FIELDS = {
        "referenceId", "version"
    };

    public ReferenceIDComparator() {

        this(false);
    }

    public ReferenceIDComparator(boolean ascending) {

        super(ORDER_BY_ASC, ORDER_BY_DESC, ORDER_BY_FIELDS, ascending);
    }

    @Override
    public int doCompare(Reference o1, Reference o2) {

        int value = o1.getReferenceId().toLowerCase().compareTo(
            o2.getReferenceId().toLowerCase());

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
