
package hu.webtown.liferay.portlet.reference.web.internal.search;

import com.liferay.admin.kernel.util.PortalProductMenuApplicationType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.search.RowMover;
import com.liferay.portal.kernel.dao.search.RowMoverDropTarget;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.trash.kernel.model.TrashEntry;

public class ReferencesMover extends RowMover {

    public ReferencesMover(boolean trashEnabled)
        throws PortalException {

        if (trashEnabled) {
            RowMoverDropTarget moveToTrashRowMoverDropTarget =
                new RowMoverDropTarget();

            moveToTrashRowMoverDropTarget.setAction("move-to-trash");
            moveToTrashRowMoverDropTarget.setActiveCssClass("bg-info");
            moveToTrashRowMoverDropTarget.setContainer("body");
            moveToTrashRowMoverDropTarget.setInfoCssClass("bg-primary");

            String productMenuPortletId = PortletProviderUtil.getPortletId(
                PortalProductMenuApplicationType.ProductMenu.CLASS_NAME,
                PortletProvider.Action.VIEW);

            String trashPortletId = PortletProviderUtil.getPortletId(
                TrashEntry.class.getName(), PortletProvider.Action.VIEW);

            moveToTrashRowMoverDropTarget.setSelector(
                StringBundler.concat(
                    "#_", productMenuPortletId, "_portlet_", trashPortletId));

            addRowMoverDropTarget(moveToTrashRowMoverDropTarget);
        }
    }

}
