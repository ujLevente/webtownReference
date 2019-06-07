AUI.add(
    'reference-dxp-navigation',
    function(A) {
	var Lang = A.Lang;

	var ReferenceNavigation = A.Component.create(
	    {
		ATTRS: {
		    editReferenceUrl: {
			validator: Lang.isString
		    },

		    form: {
			validator: Lang.isObject
		    },

		    searchContainerId: {
			validator: Lang.isString
		    }
		},

		AUGMENTS: [Liferay.PortletBase],

		EXTENDS: A.Base,

		NAME: 'referencenavigation',

		prototype: {
		    initializer: function(config) {
			var instance = this;

			var namespace = instance.NS;

			var searchContainer = Liferay.SearchContainer.get(namespace + instance.get('searchContainerId'));

			searchContainer.registerAction('move-to-trash', A.bind('_moveToTrash', instance));

			instance._searchContainer = searchContainer;

			instance._bindUI();
		    },

		    desctructor: function() {
			var instance = this;

			(new A.EventHandle(instance._eventHandles)).detach();
		    },

		    _bindUI: function() {
			var instance = this;

			instance._eventHandles = [
			    Liferay.on(instance.ns('editReference'), instance._editReference, instance)
			];
		    },

		    _editReference: function(event) {
			var instance = this;

			var action = event.action;

			var url = instance.get('editReferenceUrl');

			instance._processAction(action, url);
		    },

		    _moveToTrash: function() {
			var instance = this;

			instance._processAction('moveReferencesToTrash', instance.get('editReferenceUrl'));
		    },

		    _processAction: function(action, url, redirectUrl) {
			var instance = this;

			var namespace = instance.NS;

			var form = instance.get('form').node;

			redirectUrl = redirectUrl || location.href;

			form.attr('method', instance.get('form').method);

			if (form.get(namespace + 'javax-portlet-action')) {

			    form.get(namespace + 'javax-portlet-action').val(action);
			} else {

			    form.get(namespace + 'cmd').val(action);
			}

			form.get(namespace + 'redirect').val(redirectUrl);

			submitForm(form, url);
		    }
		}
	    }
	);

	Liferay.Portlet.ReferenceNavigation = ReferenceNavigation;
    },
    '',
    {
	requires: ['aui-component', 'liferay-portlet-base', 'liferay-search-container']
    }
);