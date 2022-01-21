<%@ page session="true" %>
    <jsp:include page="layouts/header.jsp" />
    <% if (request.getSession().getAttribute("username")==null) {
        response.sendRedirect(request.getContextPath()+"/login"); } %>

        <div class="mt-12">
            <div class="col-span-3 bg-white p-6 rounded-xl border border-gray-50 flex flex-col space-y-6">
                <div class="flex justify-between items-center">
                    <h2 class="text-sm text-gray-600 font-bold tracking-wide">Historique reservation:</h2>
                </div>
                <ul class="divide-y-2 divide-gray-100 overflow-x-auto w-full">
                    <li class="py-3 flex justify-between text-sm text-gray-500 font-semibold">
                        <p class="px-4 font-semibold">Section</p>
                        <p class="px-4 text-gray-600">Place</p>
                        <p class="px-4 tracking-wider">Type</p>
                        <p class="px-4 text-blue-600">Date</p>
                    </li>
                    <div id="reservation">
                    </div>
                </ul>
            </div>
        </div>
        <jsp:include page="layouts/footer.jsp" />