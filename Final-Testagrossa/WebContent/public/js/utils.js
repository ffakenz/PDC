const jUtils = {
    
    executing: (divId) => {
        $('#' + divId).html("<img src=\"./public/img/iloader.gif\" border=\"0\"/>").show();
    },
    
    showing: (divId, html) => {
    	const innerHtml = html !== null ? 
    			html : '';
        $('#' + divId).html(innerHtml).show();
    },
    
    hiding: (divId, clean) => {
        clean = clean === false ? 
        		false : 
    			true;
        $('#' + divId).hide();
        if(clean)
            $('#' + divId).html('&nbsp;'); 
    }
    
};


