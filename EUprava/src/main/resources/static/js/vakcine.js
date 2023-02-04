	
var baseURL = ""

function popuniBaseURL() {
	// traži od servera baseURL
	$.get("", function(odgovor) { // GET zahtev
		console.log(odgovor)

		if (odgovor.status == "ok") {
			baseURL = odgovor.baseURL // inicjalizuj globalnu promenljivu baseURL
		}
	})
	console.log("GET: " + "baseURL")
}
$(document).ready(function() {
	popuniBaseURL() // dobavi i ugradi u HTML informaciju o baseURL-u
	
	// keširajne referenci na elemente
	var tabela = $("table.tabela")
	var imeInput = tabela.find("input[name=ime]")
	var dostupnaKolicinaMinInput = tabela.find("input[name=dostupnaKolicinaMin]")
	var dostupnaKolicinaMaxInput = tabela.find("input[name=dostupnaKolicinaMax]")
    var proizvodjacIdSelect = tabela.find("select[name=proizvodjacId]")
    var proizvodjacIdDrzavaSelect = tabela.find("select[name=proizvodjacIdDrazava]")

	function popuniVakcine() {
		// čitanje parametara forme za pretragu
		var ime = imeInput.val()
		var dostupnaKolicinaMin = dostupnaKolicinaMinInput.val()
		var dostupnaKolicinaMax = dostupnaKolicinaMaxInput.val()
        var proizvodjacId = proizvodjacIdSelect.find("option:selected").val()
        var proizvodjacIdDrzava = proizvodjacIdDrzavaSelect.find("option:selected").val()

		// parametri zahteva
		var params = {
			ime: ime, 
            dostupnaKolicinaMin: dostupnaKolicinaMin,
			dostupnaKolicinaMax: dostupnaKolicinaMax,
			prizvodjacId: proizvodjacId,
            prizvodjacIdDrzava: proizvodjacIdDrzava
			
		}
		console.log(params)
		$.get(baseURL+"vakcine/search", params, function(odgovor) {
			console.log(odgovor)
			
			if (odgovor.status == "ok") {
				tabela.find("tr:gt(1)").remove() // ukloni sve redove u tabeli iza forme za pretragu
				
				var vakcine = odgovor.vakcine
				for (var itVakcina in vakcine) {
					tabela.append( // kreiraj, popuni red i dodaj ga u tabelu
						'<tr>' + 
							'<td class="broj">' + (parseInt(itVakcina) + 1) + '</td>' + 
							'<td><a href="vakcina.html?id=' + vakcine[itVakcina].id + '">' + vakcine[itVakcina].ime + '</a></td>' + 
							'<td>' + vakcine[itVakcina].ime + '</td>' + 
                            '<td class="broj">' + vakcine[itVakcina].dostupnaKolicina + '</td>' + 
                            '<td>' + vakcine[itVakcina].proizvodjac.proizvodjac + '</td>' + 
                            '<td>' + vakcine[itVakcina].proizvodjac.drzavaProizvodnje + '</td>' + 
						'</tr>'
					)
					
				}
			}
		})
		console.log("GET: " + "Vakcine")
	}

	
	$(".specific-submit").click(function(event) {
    event.preventDefault();
    popuniVakcine();
	});
	
	$("form").submit(function() { // registracija handler-a za pretragu
		popuniVakcine()

		return false // sprečiti da submit forme promeni stranicu
	})
})