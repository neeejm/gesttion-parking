<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")==null) {
        response.sendRedirect(request.getContextPath()+"/login"); } %>
        <!-- Start Main -->
        <main class="bg-white max-w-lg mx-auto p-8 md:p-12 my-10 rounded-lg shadow-2xl">
            <section>
                <h3 class="font-bold text-2xl">Gestion reservation</h3>
            </section>

            <section class="mt-10">
                <div class="flex flex-col">
                    <div class="mb-6 pt-3 rounded bg-gray-200">
                        <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="mat">Matricule</label>
                        <input type="matricule" id="mat" name="mat" minlength="3"
                            class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                            required>
                    </div>
                </div>
            </section>
        </main>
        <div class="mt-12">
            <div class="col-span-3 bg-white p-6 rounded-xl border border-gray-50 flex flex-col space-y-6">
                <div class="flex justify-between items-center">
                    <h2 class="text-sm text-gray-600 font-bold tracking-wide">Liste Places a reserver:</h2>
                </div>
                <ul class="divide-y-2 divide-gray-100 overflow-x-auto w-full">
                    <li class="py-3 flex justify-between text-sm text-gray-500 font-semibold">
                        <p class="px-4 font-semibold">Section</p>
                        <p class="px-4 text-gray-600">Place</p>
                        <p class="px-4 tracking-wider">Type</p>
                        <p class="px-4 text-blue-600">Etat</p>
                        <a href="#"
                            class="px-4 py-2 text-xs bg-blue-100 text-blue-500 rounded uppercase tracking-wider font-semibold hover:bg-blue-300">Bouton</a>
                    </li>
                    <div id="reserver">
                    </div>
                </ul>
            </div>
        </div>
        <jsp:include page="layouts/footer.jsp" />