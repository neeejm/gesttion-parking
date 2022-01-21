<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>EParking</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />

    <link rel="stylesheet" href="https://unpkg.com/tailwindcss@2.2.19/dist/tailwind.min.css" />
    <!--Replace with your tailwind.css once created-->
    <link href="https://unpkg.com/@tailwindcss/custom-forms/dist/custom-forms.min.css" rel="stylesheet" />

    <style>
        @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap");

        html {
            font-family: "Poppins", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        }
    </style>
</head>

<body class="leading-normal tracking-normal text-indigo-400 m-6 bg-cover bg-fixed"
    style="background-image: url('assets/header.png');">
    <div class="h-full">
        <!--Nav-->
        <div class="w-full container mx-auto">
            <div class="w-full flex items-center justify-between">
                <a class="flex items-center text-indigo-400 no-underline hover:no-underline font-bold text-2xl lg:text-4xl"
                    href="index.jsp">
                    Epar<span
                        class="bg-clip-text text-transparent bg-gradient-to-r from-green-400 via-pink-500 to-purple-500">king</span>
                </a>
                <% if (request.getSession().getAttribute("username")!=null) { %>
                    <% if (request.getSession().getAttribute("username").equals("admin")) { %>
                        <div class="flex w-1/2 justify-end content-center">
                            <button
                                class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out mx-6"
                                type="button">
                                <a href="sections.jsp">
                                    Gestion sections
                                </a>
                            </button>
                            <button
                                class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                                type="button">
                                <a href="sections.jsp">
                                    Gestion places
                                </a>
                            </button>
                        </div>
                        <% } else { %>
                            <div class="flex w-1/2 justify-end content-center">
                                <button
                                    class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out mx-6"
                                    type="button">
                                    <a href="reserver.jsp">
                                        Reserver
                                    </a>
                                </button>
                                <button
                                    class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                                    type="button">
                                    <a href="reservation.jsp">
                                        Reservation
                                    </a>
                                </button>
                            </div>
                            <% } %>
                                <div class="flex w-1/2 justify-end content-center">
                                    <p
                                        class="bg-gray-900 font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out mx-6">
                                        <% out.println(request.getSession().getAttribute("username")); %>
                                    </p>
                                    <button
                                        class="bg-gradient-to-r from-purple-800 to-green-500 hover:from-pink-500 hover:to-green-500 text-white font-bold py-2 px-4 rounded focus:ring transform transition hover:scale-105 duration-300 ease-in-out"
                                        type="button">
                                        <a href="logout">
                                            Se Deconnecter
                                        </a>
                                    </button>
                                </div>
                                <% } %>
            </div>
        </div>
    </div>