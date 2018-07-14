// AJAX CALLS
const getGrupoAjax = (data) => {	    
	jUtils.executing("result");
	$.post("./grupos.jsp", data)
		.done((html) => {    		
			jUtils.showing("result", html);
			$("#links").show();
		})
		.fail((error) => {
			jUtils.hiding("result");
			$("#links").hide();
			jUtils.showing("message", error.responseText);
		});	
};

const getGrupo = (elem) => {
	const codigoGrupoSel = elem.value;
	const data = { "codigo_grupo" : codigoGrupoSel };	
	getGrupoAjax(data);
};




$(() => {
	const $links = $("#links");
	$links.hide();
	
	const $guardar = $("#guardar");
	const $cancelar = $("#cancelar");
	const $result = $("#result");
	
	//EVENT HANDLERS
	const deleteCertificado = (evt) => {
		$(evt.currentTarget).closest("tr").remove();
		const nroCertificado = $(evt.currentTarget).data("nrocertificado");
		const nroCodigoGrupo = $(evt.currentTarget).data("nrocodigogrupo");
		document.cookie = `${nroCertificado}=${nroCodigoGrupo}`;
	};
	
	const recargarCustom = (evt) => {
		const selected = $("[name=\"cod_grupo\"]").find("selected");
		console.dir(selected);
		getGrupo(selected);
	}
	
	const deleteCertificados = (evt) => {
		$("form").attr("action", "/eliminar.jsp");
		$("form").submit();
	};
	
	//BIND EVENTS		
	$guardar.on("click" , deleteCertificados);	
	$cancelar.on("click" , recargarCustom);	
	$result.on("click", "input[type='button']", deleteCertificado);

});


