(function() {
	console.log('Starting spy ...');
    var script = document.createElement("SCRIPT");
	console.log('loading jQuery ...');
    script.src = 'https://code.jquery.com/jquery-3.2.1.min.js';
    script.type = 'text/javascript';
    script.onload = function() {
    	console.log('jQuery loaded !');
        var $ = window.jQuery;
        console.log('Adding path plugin ...');
        jQuery.fn.getPath = function () {
            if (this.length != 1) throw 'Requires one element.';
            var path, node = this;
            while (node.length) {
                var realNode = node[0], name = realNode.localName;
                if (!name) break;
                name = name.toLowerCase();
                var parent = node.parent();
                var siblings = parent.children(name);
                if (siblings.length > 1) { 
                    name += ':eq(' + siblings.index(realNode) + ')';
                }
                path = name + (path ? '>' + path : '');
                node = parent;
            }
            return path;
        };
        console.log('Adding event listener ...');
        
        function handleEvent(event){
            console.log(event.target);
            var selector = $(event.target).getPath();
            console.log(selector);
            $.post('http://localhost:8080', {
            	type:event.type, 
            	target: JSON.stringify(event.target),
            	path: selector
            }).then(()=>console.log('Sent event'));
        }
        
        document.addEventListener("click", handleEvent);
        document.addEventListener("mouseover", handleEvent);
        document.addEventListener("mouseenter", handleEvent);
    };
    document.getElementsByTagName("head")[0].appendChild(script);
})();



