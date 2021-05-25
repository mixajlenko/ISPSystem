<!DOCTYPE html>
<head>
    <title>ServicesUser</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/nicepage.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/template.css" media="screen">
</head>
<body class="u-body">
<section class="u-align-center u-clearfix u-hidden-md u-hidden-sm u-hidden-xs u-section-1" id="sec-be76">
    <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-container-style u-gradient u-group u-hidden-md u-hidden-sm u-hidden-xs u-group-1">
            <div class="u-container-layout u-valign-middle-xl u-container-layout-1">
                <h1 class="u-align-left u-text u-text-default u-text-1">ISPAdmin</h1>
                <p class="u-text u-text-default u-text-2">Date/Time: <span id="datetime"></span></p>
                <script src="${pageContext.request.contextPath}/js/time.js"></script>
            </div>
        </div>
        <div class="u-border-1 u-border-grey-75 u-container-style u-group u-white u-group-2">
            <div class="u-container-layout u-container-layout-2">
                <h2 class="u-align-center u-text u-text-3">Main page</h2>
                <h4 class="u-align-left u-text u-text-4">Clients count: ${users}</h4>
                <h4 class="u-align-left u-text u-text-6">Active clients: ${active}</h4>
                <h4 class="u-align-left u-text u-text-8">Blocked clients : ${blocked}</h4>
            </div>
        </div>
        <div class="u-border-1 u-border-grey-75 u-container-style u-group u-hidden-md u-hidden-sm u-hidden-xs u-white u-group-3">
            <div class="u-container-layout u-valign-top u-container-layout-3">
                <a href="${pageContext.request.contextPath}/view/admin/mainPageAdmin"
                   class="u-border-0 u-btn u-button-style u-none u-text-palette-5-dark-1 u-btn-2">Main page</a>
                <a href="${pageContext.request.contextPath}/view/admin/servicePageAdmin"
                   class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-style u-none u-text-palette-5-dark-1 u-btn-2">Services</a>
                <a href="${pageContext.request.contextPath}/view/admin/userPageAdmin"
                   class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-style u-none u-text-palette-5-dark-1 u-btn-3">Clients</a>
                <a href="${pageContext.request.contextPath}/view/logout"
                   class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-style u-none u-text-palette-5-dark-1 u-btn-4">Log
                    Out</a>
            </div>
        </div>
    </div>
</section>
</body>
</html>