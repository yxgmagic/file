var formcheck = {
		ctxPath: "",
 
		initValidator: function(formId){
			  
			console.log("formId=="+formId);
			console.log("fields=="+formcheck.fields);
			$('#' + formId).bootstrapValidator({
				
				feedbackIcons: {
					valid: 'glyphicon glyphicon-ok',
					invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
				},
				fields: formcheck.fields,
				live: 'enabled',
				message: '该字段不能为空'
			});
		} ,
		fields: {
			roadwidth: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					}
				}
			},

			sitecode: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					}
				}
			},
			manager: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					}
				}
			},
			ulcode: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					}
				}
			},
			managertel: {
				validators: {
					notEmpty: {
						message: '不能为空!'
					},
					regexp: {
						regexp: /^1[34578]\d{9}$/,
						message: '手机号码格式错误!'
					}
				}
			},
		}
};
