<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")!=null) {
        response.sendRedirect(request.getContextPath()+"/home.jsp"); } %>
        <!--Main-->
        <main class="bg-white max-w-lg mx-auto p-8 md:p-12 my-10 rounded-lg shadow-2xl">
            <section>
                <h3 class="font-bold text-2xl">Bienvenue sur EParking</h3>
            </section>

            <section class="mt-10">
                <form class="flex flex-col" method="POST" action="login">
                    <% if (request.getAttribute("error") !=null) { %>
                        <p class="text-red-600 pt-2 mb-2">Username out mots de passe incorrecte!</p>
                        <% } %>
                            <div class="mb-6 pt-3 rounded bg-gray-200">
                                <label class="block text-gray-700 text-sm font-bold mb-2 ml-3"
                                    for="email">Username</label>
                                <input type="text" id="username" name="username"
                                    class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                                    required>
                            </div>
                            <div class="mb-6 pt-3 rounded bg-gray-200">
                                <label class="block text-gray-700 text-sm font-bold mb-2 ml-3"
                                    for="password">Password</label>
                                <input type="password" id="password" name="password"
                                    class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-purple-600 transition duration-500 px-3 pb-3"
                                    required>
                            </div>
                            <button
                                class="bg-purple-600 hover:bg-purple-700 text-white font-bold py-2 rounded shadow-lg hover:shadow-xl transition duration-200"
                                type="submit">Se Connecter</button>
                </form>
            </section>
        </main>

        <div class="max-w-lg mx-auto text-center mt-12 mb-6">
            <p class="text-white">Vous n'avez pas un compte? <a href="register.jsp"
                    class="font-bold hover:underline">S'inscrire</a>.</p>
        </div>
        <jsp:include page="layouts/footer.jsp" />