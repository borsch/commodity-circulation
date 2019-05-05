(function(exports){

	exports.get = function(options){
		options = options || {};
		options.type = 'GET';

		var data = options.data;
		if (data) {
			var query = [];

			for (var key in data) {
				query.push(encodeURIComponent(key + '=' + data[key]));
			}

			if (query.length) {
				options.url = options.url + '?' + query.join('&');
			}
		}

		_ajax(options);
	};

	exports.put = function(options){
		putAndPost(options, 'PUT');
	};

	exports.post = function(options){
		putAndPost(options, 'POST');
	};

	/**
	 * @description detect which method must be invoked based on `id` property value
	 *              if `id` is `null`, `undefined`, `0` or any other value which equals to `false`
	 *              than `put` will be executed, otherwise `post`
	 *
	 * @param options
	 */
	exports.save = function(options) {
		var data = options.data;
		if (!data) {
			throw new Error('data is required property');
		}

		var method = (data.id ? exports.post : exports.put);
		method(options);
	};

	exports.delete = function(options){
		options = options || {};
		options.type = 'DELETE';
		_ajax(options);
	};

	function _ajax(options){
		options.beforeSend = function(xhr){
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.setRequestHeader('Accept', 'application/json');
		};
		$.ajax(options);
	}

	function putAndPost(options, type) {
		$.ajax({
			url: options.url,
			type: type,
			data: JSON.stringify(options.data),
			dataType: 'json',
			beforeSend: function(xhr){
				xhr.setRequestHeader('Accept', 'application/json');
				xhr.setRequestHeader('Content-Type', 'application/json');
			},
			success: options.success,
			error: options.error || console.log
		});
	}

})(window.Ajax = {});