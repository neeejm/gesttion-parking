const sectionHtml = (code, id) => {
    return `
        <div class="bg-white max-w-lg mx-auto p-8 md:p-12 my-10 rounded-lg shadow-2xl">
            <section>
                <h3 class="font-bold text-6xl">${code}</h3>
            </section>

            <div class="mt-10">
            <button
                class="bg-purple-600 hover:bg-purple-700 text-white font-bold py-2 rounded shadow-lg hover:shadow-xl transition duration-200"
                value="${id}" onclick="remove(${id})">Supprimer</button>
            </div>
        </div>
    `;
}

const placeHtml = (code, num, type, etat, id_place) => {
    type = (type == 1) ? "Voiture" : "moto";
    return `
        <li class="py-3 flex justify-between text-sm text-gray-500 font-semibold">
            <p class="px-4 font-semibold">${code}</p>
            <p class="px-4 text-gray-600">${num}</p>
            <p class="px-4 tracking-wider">${type}</p>
            <p class="px-4 text-blue-600">${etat}</p>
            <a href="#"
                class="px-4 py-2 text-xs bg-blue-100 text-blue-500 rounded uppercase tracking-wider font-semibold hover:bg-blue-300"
                onclick="removePlace(${id_place})">supprimer</a>
        </li>
    `;
}


const getSections = () => {
    $("#sections").empty();
    fetch(`http://localhost:4000/eparking/api/v1/section`)
        .then(response => response.json())
        .then(result => {
            result.forEach(element => {
                // console.log(element.code, element.id);
                $("#sections").append(sectionHtml(element.code, element.id));
            });
        })
        .catch(error => console.log('error', error));
}

const getSectionsDropDown = () => {
    fetch(`http://localhost:4000/eparking/api/v1/section`)
        .then(response => response.json())
        .then(result => {
            result.forEach(element => {
                // console.log(element.code, element.id);
                $("#sec").append(`<option value="${element.id}">${element.code}</option>`);
            });
        })
        .catch(error => console.log('error', error));
}

const getPlaces = () => {
    $("#places").empty();
    fetch(`http://localhost:4000/eparking/api/v1/place`)
        .then(response => response.json())
        .then(result => {
            result.forEach(element => {
                // console.log(element.code, element.id);
                $("#places").append(placeHtml(element.section.code, element.numero, element.type, element.etat, element.id));
            });
        })
        .catch(error => console.log('error', error));
}

$("#secttion-page").on("load", getSections());
$("#sec").on("load", getSectionsDropDown());
$("#places").on("load", getPlaces());

$("#generator").on("click", () => {
    let gen = "cx";
    if ($('#abc').is(':checked')) { gen = "abc" }
    let num = $("#num").val();
    fetch(`http://localhost:4000/eparking/api/v1/section?gen=${gen}&num=${num}`)
        .then(response => response.json())
        .then(result => {
            console.log(result);
            getSections();
        })
        .catch(error => console.log('error', error));
    // console.log()
    // getSections();
})

$("#add").on("click", () => {
    let code = $("#code").val();
    fetch(`http://localhost:4000/eparking/api/v1/section`, {
        method: "POST",
        body: JSON.stringify({
            code: code
        })
    })
        .then(response => response.json())
        .then(result => {
            console.log(result);
            getSections();
        })
        .catch(error => console.log('error', error));
    // console.log()
    // getSections();
})

const remove = (id) => {
    // console.log(id)
    fetch(`http://localhost:4000/eparking/api/v1/section?id=${id}`, {
        // mode: "no-cors",
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(result => {
            console.log(result);
            getSections();
        })
        .catch(error => console.log('error', error));
}

$("#add-place").on("click", () => {
    let type = 1;
    if ($('#moto').is(':checked')) { type = 2 }
    let num = $("#num").val();
    let code = $("#sec").val();
    fetch(`http://localhost:4000/eparking/api/v1/place`, {
        method: "POST",
        body: JSON.stringify(
            {
                numero: num,
                etat: false,
                type: type,
                section: {
                    id: code
                }
            }
        )
    })
        .then(response => response.json())
        .then(result => {
            console.log(result);
            getPlaces();
        })
        .catch(error => console.log('error', error));
    // console.log()
    // getSections();
})

const removePlace = (id) => {
    // console.log(id)
    fetch(`http://localhost:4000/eparking/api/v1/place?id=${id}`, {
        // mode: "no-cors",
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(result => {
            console.log(result);
            getPlaces();
        })
        .catch(error => console.log('error', error));
}