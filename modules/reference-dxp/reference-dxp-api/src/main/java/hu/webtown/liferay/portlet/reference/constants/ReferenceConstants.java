
package hu.webtown.liferay.portlet.reference.constants;

import com.liferay.portal.kernel.workflow.WorkflowConstants;

public class ReferenceConstants {

    public static final String CANONICAL_URL_SEPARATOR = "/-/";
    
    public static final String RESOURCE_NAME = "hu.webtown.liferay.portlet.reference";

    public static final double VERSION_DEFAULT = 1.0;

    private static final int[] ASSET_ENTRY_CREATION_STATUSES = {
        WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_EXPIRED
    };

    private ReferenceConstants() {

    }

    public static int[] getAssetEntryCreationStatuses() {

        return ASSET_ENTRY_CREATION_STATUSES.clone();
    }

}
