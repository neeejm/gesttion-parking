<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")==null) {
        response.sendRedirect(request.getContextPath()+"/login"); } %>
        <!-- Start Main -->
        <main id="stats" class="container mx-w-6xl mx-auto py-4 mt-12">
            <div class="flex flex-col space-y-8">
                <!-- Start Second Row -->
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 px-4 xl:p-0 gap-4 xl:gap-6">
                    <div class="col-span-1 md:col-span-2 lg:col-span-4 flex justify-between">
                        <h2 class="text-xs md:text-sm text-white-700 font-bold tracking-wide md:tracking-wider">
                            General statistique sur les places, sections et reservations:</h2>
                        <a href="#" class="text-xs text-gray-800 font-semibold uppercase">More</a>
                    </div>
                    <div class="bg-white p-6 rounded-xl border border-gray-50">
                        <div class="flex justify-between items-start">
                            <div class="flex flex-col">
                                <p class="text-xs text-gray-600 tracking-wide">Nombre de sections</p>
                                <h3 class="mt-1 text-lg text-blue-500 font-bold" id="num_section"></h3>
                            </div>
                        </div>
                    </div>
                    <div class="bg-white p-6 rounded-xl border border-gray-50">
                        <div class="flex justify-between items-start">
                            <div class="flex flex-col">
                                <p class="text-xs text-gray-600 tracking-wide">Nombre de places</p>
                                <h3 class="mt-1 text-lg text-blue-500 font-bold" id="num_place"></h3>
                            </div>
                        </div>
                    </div>
                    <div class="bg-white p-6 rounded-xl border border-gray-50">
                        <div class="flex justify-between items-start">
                            <div class="flex flex-col">
                                <p class="text-xs text-gray-600 tracking-wide">Nombre d'utilisateur</p>
                                <h3 class="mt-1 text-lg text-blue-500 font-bold" id="num_user"></h3>
                            </div>
                        </div>
                    </div>
                    <div class="bg-white p-6 rounded-xl border border-gray-50">
                        <div class="flex justify-between items-start">
                            <div class="flex flex-col">
                                <p class="text-xs text-gray-600 tracking-wide">Nombre de reservation</p>
                                <h3 class="mt-1 text-lg text-blue-500 font-bold" id="num_reservation"></h3>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Second Row -->
                <!-- Start Third Row -->
                <div class="grid grid-cols-1 md:grid-cols-5 items-start px-4 xl:p-0 gap-y-4 md:gap-6">
                    <div class="col-start-1 col-end-5">
                        <h2 class="text-xs md:text-sm text-white-800 font-bold tracking-wide">Nombre de reservation par
                            section</h2>
                    </div>
                    <div class="col-span-2 bg-white p-6 rounded-xl border border-gray-50 flex flex-col space-y-6">
                        <canvas id="myChart"></canvas>
                    </div>
                </div>
                <!-- End Third Row -->
            </div>
        </main>
        <!-- End Main -->
        <jsp:include page="layouts/footer.jsp" />