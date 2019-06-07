AUI.add(
    'reference-dxp-portlet',
    function(A) {
	var Lang = A.Lang;

        var SELECTOR_ACTION_NAME = '#javax-portlet-action';

        var STR_ACTION_NAME = 'javax.portlet.action';

        var STR_ADD_REFERENCE = 'addReference';

        var STR_REFERENCE_ID = 'referenceId';

        var STR_CLICK = 'click';

        var STR_UPDATE_REFERENCE = 'updateReference';
        
        var Reference = A.Component.create(
            {
        	ATTRS: {
        	    referenceId: {
        		validator: Lang.isString,
        		value: ''
        	    }
        	},
        	
        	AUGMENTS: [Liferay.PortletBase],
        	
        	EXTENDS: A.Base,
        	
        	NAME: 'reference',
        	
        	prototype: {
        	    initializer: function() {
        		var instance = this;
        		
        		instance._bindUI();
        	    },
        	    
        	    destructor: function() {
        		var instance = this;
        		
        		(new A.EventHandle(instance._eventHandles)).detach();
        	    },
        	    
        	    _bindUI: function() {
        		var instance = this;
        		
        		var form = instance._getPrincipalForm();
        		
        		var eventHandles = [
        		    form.delegate('change', instance._onFormChanged, ':input', instance),
        		    form.on('submit', instance._onFormSubmit, instance)
        		];
        		
        		var buttonRow = instance.one('.reference-button-row');
        		
        		if (buttonRow) {
        		    
        		    eventHandles.push(buttonRow.delegate(STR_CLICK, instance._onButtonClick, 'button', instance));
        		}
        		
        		var nameInput = instance._getByName(form, "name");
        		
        		if (nameInput) {
        		    eventHandles.push(nameInput.on('blur', instance._onNameInputBlur, instance));
        		}
        		
        		instance._eventHandles = eventHandles;
        	    },
        	    
        	    _getByName: function(currentForm, name, withoutNamespace) {
        		var instance = this;
        		
        		if (!withoutNamespace) {
        		    
        		    name = instance.ns(name);
        		}
        		
        		return instance.one('[name=' + name + ']', currentForm);
        	    },
        	    
        	    _getPrincipalForm: function(formName) {
        		var instance = this;
        		
        		return instance.one('form[name=' + instance.ns(formName || 'fm') + ']');
        	    },
        	    
        	    _onButtonClick: function(event) {
        		var instance = this;
        		
        		var actionName = event.currentTarget.attr('data-actionname');
        		
        		if (actionName) {
        		    
        		    var form = instance._getPrincipalForm();
        		    
        		    instance.one(SELECTOR_ACTION_NAME, form).val(actionName);
        		}
        	    },
        	    
        	    _onFormChanged: function(event) {
        		var instance = this;
        		
        		instance._formChanged = true;
        	    },
        	    
        	    _onFormSubmit: function(event) {
        		var instance = this;
        		
        		event.preventDefault();
        		
        		var form = instance._getPrincipalForm();
        		
        		var actionName = instance.one(SELECTOR_ACTION_NAME, form).val();
        		
        		instance._saveReference(actionName);
        	    },
        	    
        	    _onNameInputBlur: function(event) {
        		var instance = this;

        		var form = instance._getPrincipalForm();
        		
        		var urlTitleInput = instance._getByName(form, "urlTitle");
        		
        		if (urlTitleInput && !urlTitleInput.val()) {
        		    
        		    Liferay.Service(
    			    	'/reference.reference/get-unique-url-title',
    			    	{
    			    	    groupId: themeDisplay.getScopeGroupIdOrLiveGroupId(),
    			    	    name: event.currentTarget.val()
    			    	},
    			    	function(obj) {
    			    	    if (!A.Object.hasKey(obj, 'exception')) {
    			    		urlTitleInput.val(obj);
    			    	    } else {
    			    		// ignore
    			    	    }
    			    	}
        		    );
        		}
        	    },
        	    
        	    _saveReference: function(actionName) {
        		var instance = this;
        		
        		var form = instance._getPrincipalForm();
        		
        		var referenceId = instance.get(STR_REFERENCE_ID);
        		
        		if (actionName === 'publish') {
        		    
        		    var workflowActionInput = instance._getByName(form, 'workflowAction');
        		    
        		    workflowActionInput.val(Liferay.Workflow.ACTION_PUBLISH);
        		    
        		    actionName = null;
        		}
        		
        		if (!actionName) {
        		    
        		    actionName = referenceId ? STR_UPDATE_REFERENCE : STR_ADD_REFERENCE;
        		}
        		
        		var actionInputName = instance._getByName(form, STR_ACTION_NAME);
        		
        		actionInputName.val(actionName);
        		
        		submitForm(form);
        	    }
        	}
            }
    	);
        
        Liferay.Portlet.Reference = Reference;
    },
    '',
    {
	requires: ['aui-base', 'liferay-portlet-base']
    }
);