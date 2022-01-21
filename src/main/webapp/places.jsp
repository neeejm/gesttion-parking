<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")==null) {
        response.sendRedirect(request.getContextPath()+"/login"); } %>
        <!-- Start Main -->
        <main class="bg-white max-w-lg mx-auto p-8 md:p-12 my-10 rounded-lg shadow-2xl">
            <section>
                <h3 class="font-bold text-2xl">Gestion Places</h3>
            </section>

            <section class="mt-10">
                <div class="flex flex-col">
                    <div class="mb-6 pt-3 rounded bg-gray-200">
                        <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="number">Numero de
                            place</label>
                        <input type="number" id="num" name="num" min="1"
                            class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                            required>
                    </div>
                    <div class="mb-6 pt-3 rounded bg-gray-200">
                        <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="sec">Section</label>
                        <!-- <label for="sec">Choosit une section :</label> -->
                        <select name="sec" id="sec">
                        </select>


                        <!-- <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="number">Section</label> -->
                        <!-- <input type="text" id="section" name="section"
                            class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                            required> -->
                    </div>
                    <div class=" mb-6 pt-3 rounded bg-gray-200">
                        <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="email">Type</label>
                        <div class="flex">
                            <label class="inline-flex items-center ml-3">
                                <input type="radio" name="car" value="2" id="moto"
                                    class="form-radio h-5 w-5 text-gray-600"><span
                                    class="ml-2 text-gray-700">Moto</span>
                            </label>

                            <label class="inline-flex items-center ml-3">
                                <input type="radio" name="car" value="1" id="car"
                                    class="form-radio h-5 w-5 text-red-600" checked><span
                                    class="ml-2 text-gray-700">Voiture</span>
                            </label>
                        </div>
                    </div>
                    <button
                        class="bg-purple-600 hover:bg-purple-700 text-white font-bold py-2 rounded shadow-lg hover:shadow-xl transition duration-200"
                        id="add-place">Ajouter</button>
                </div>
            </section>
        </main>
        <div>
            <div class="col-span-3 bg-white p-6 rounded-xl border border-gray-50 flex flex-col space-y-6">
                <div class="flex justify-between items-center">
                    <h2 class="text-sm text-gray-600 font-bold tracking-wide">Liste Places:</h2>
                </div>
                <ul class="divide-y-2 divide-gray-100 overflow-x-auto w-full">
                    <li class="py-3 flex justify-between text-sm text-gray-500 font-semibold">
                        <p class="px-4 font-semibold">Section</p>
                        <p class="px-4 text-gray-600">Place</p>
                        <p class="px-4 tracking-wider">Type</p>
                        <p class="px-4 text-blue-600">Etat</p>
                        <p class="px-4 text-blue-600">Supprimer</p>
                        <p class="px-4 text-blue-600">Annuler</p>
                    </li>
                    <div id="places">
                    </div>
                </ul>
            </div>
        </div>
        <jsp:include page="layouts/footer.jsp" />