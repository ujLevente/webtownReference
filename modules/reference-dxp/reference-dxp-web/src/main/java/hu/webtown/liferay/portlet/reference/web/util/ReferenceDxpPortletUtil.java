
package hu.webtown.liferay.portlet.reference.web.util;

import com.liferay.portal.kernel.util.OrderByComparator;

import hu.webtown.liferay.portlet.reference.model.Reference;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceCreateDateComparator;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceIDComparator;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceModifiedDateComparator;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceNameComparator;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceRealizationDateComparator;
import hu.webtown.liferay.portlet.reference.util.comparator.ReferenceVersionComparator;

public class ReferenceDxpPortletUtil {

    /**
     * Private constructor. Prevents instantiation from other classes.
     */
    private ReferenceDxpPortletUtil() {

    }

    public static OrderByComparator<Reference> getReferenceOrderByComparator(
        String orderByCol, String orderByType) {

        boolean orderByAsc = false;

        if ("asc".equals(orderByType)) {

            orderByAsc = true;
        }

        OrderByComparator<Reference> orderByComparator = null;

        if ("create-date".equals(orderByCol)) {

            orderByComparator = new ReferenceCreateDateComparator(orderByAsc);
        } else if ("realization-date".equals(orderByCol)) {

            orderByComparator =
                new ReferenceRealizationDateComparator(orderByAsc);
        } else if ("id".equals(orderByCol)) {

            orderByComparator = new ReferenceIDComparator(orderByAsc);
        } else if ("modified-date".equals(orderByCol)) {

            orderByComparator = new ReferenceModifiedDateComparator(orderByAsc);
        } else if ("name".equals(orderByCol)) {

            orderByComparator = new ReferenceNameComparator(orderByAsc);
        } else if ("version".equals(orderByCol)) {

            orderByComparator = new ReferenceVersionComparator(orderByAsc);
        }

        return orderByComparator;
    }

}
