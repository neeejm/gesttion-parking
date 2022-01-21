<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")==null) {
        response.sendRedirect(request.getContextPath()+"/login"); } %>
        <!-- Start Main -->
        <main class="bg-white max-w-lg mx-auto p-8 md:p-12 my-10 rounded-lg shadow-2xl">
            <section>
                <h3 class="font-bold text-2xl">Bienvenue sur EParking</h3>
            </section>

            <section class="mt-10">
                <div class="flex flex-col"">
                    <% if (request.getAttribute(" error") !=null) { %>
                    <p class="text-red-600 pt-2 mb-2">Username out mots de passe incorrecte!</p>
                    <% } %>
                        <div class="mb-6 pt-3 rounded bg-gray-200">
                            <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="email">Comment
                                Generer</label>
                            <div class="flex">
                                <label class="inline-flex items-center ml-3">
                                    <input type="radio" name="gen" value="abc" id="abc"
                                        class="form-radio h-5 w-5 text-gray-600"><span class="ml-2 text-gray-700">A - B
                                        - C</span>
                                </label>

                                <label class="inline-flex items-center ml-3">
                                    <input type="radio" name="gen" value="cx" id="cx"
                                        class="form-radio h-5 w-5 text-red-600" checked><span
                                        class="ml-2 text-gray-700">C1 - C2 - C3</span>
                                </label>
                            </div>
                            <!-- <input type="text" id="username"
                                class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                                required> -->
                        </div>
                        <div class="mb-6 pt-3 rounded bg-gray-200">
                            <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="number">Nombre de
                                sections</label>
                            <input type="number" id="num" name="num"
                                class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                                required>
                        </div>
                        <button
                            class="bg-purple-600 hover:bg-purple-700 text-white font-bold py-2 rounded shadow-lg hover:shadow-xl transition duration-200"
                            id="generator">Generer</button>
                </div>
            </section>
        </main>
        <jsp:include page="layouts/footer.jsp" />