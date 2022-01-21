<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")!=null) {
        response.sendRedirect(request.getContextPath()+"/home.jsp"); } %>
        <!--Main-->
        <div class="container pt-24 md:pt-36 mx-auto flex flex-wrap flex-col md:flex-row items-center">
            <!--Left Col-->
            <div class="flex flex-col w-full xl:w-2/5 justify-center lg:items-start overflow-y-hidden">
                <h1
                    class="my-4 text-3xl md:text-5xl text-white opacity-75 font-bold leading-tight text-center md:text-left">
                    Reserver
                    <span
                        class="bg-clip-text text-transparent bg-gradient-to-r from-green-400 via-pink-500 to-purple-500">
                        une place
                    </span>
                    dans notre parking
                </h1>
                <div class="flex items-center justify-between pt-4 space-x-4">
                    <button
                        class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                        type="button">
                        <a href="register.jsp">
                            S'inscrire
                        </a>
                    </button>
                    <button
                        class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                        type="button">
                        <a href="login.jsp">
                            Connecter
                        </a>
                    </button>
                </div>
            </div>

            <!--Right Col-->
            <div class="w-full xl:w-3/5 p-12 overflow-hidden">
                <img class="mx-auto w-full md:w-4/5 transform -rotate-6 transition hover:scale-105 duration-700 ease-in-out hover:rotate-6"
                    src="assets/parking.svg" />
            </div>
            <jsp:include page="layouts/footer.jsp" />