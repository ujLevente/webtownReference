;(function() {
    AUI().applyConfig(
        {
            groups: {
        	reference: {
        	    base: MODULE_PATH + '/js/',
        	    combine: Liferay.AUI.getCombine(),
        	    modules: {
        		'reference-dxp-navigation': {
        		    path: 'navigation.js',
        		    requires: [
        			'aui-component',
        			'liferay-portlet-base',
        			'liferay-search-container'
        		    ]
        		},
        		'reference-dxp-portlet': {
        		    path: 'main.js',
        		    requires: [
        			'aui-base',
        			'liferay-portlet-base'
        		    ]
        		}
        	    },
        	    root: MODULE_PATH + '/js/'
        	}
            }
        }
    );
})();