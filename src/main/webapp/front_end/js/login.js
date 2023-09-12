async function checkLoginStatusShowMemberAcct() {
    const response = await fetch('/BuyGo/api/front_end/checkLoginStatus', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json;charset:utf-8' },
    })
        .then(resp => resp.json())
        .then(body => {
            const { memberNo, memberAcct, loginState } = body;
            $("nav#tiny-menu").html(`
                <ul class="user-menu">
                    <li><a href="/BuyGo/front_end/pages/member/myaccount.html">My Account</a></li>
                    <li><a href="/BuyGo/front_end/pages/member/cart.html">My Wishlist</a></li>
                    <li><a href="/BuyGo/front_end/pages/member/checkout.html">Checkout</a></li>
                </ul>
                `);
            if (loginState) { // 如果已為登入狀態
                $("nav#tiny-menu > ul.user-menu").append(`<li id="logInAndLogOut"><a href="/BuyGo/api/front_end/logOut">Log Out</a></li>`); // 修改登入登出按鈕為Log Out
                $("header#site-header").find("div.clearfix").html(`<strong style="font-weight: bold; position: absolute; right: 150px">您好, ` + memberAcct + `</strong>`); // 顯示登入者帳號資訊
                let memberDetail = [memberNo, memberAcct];
                return memberDetail; // 回傳會員詳細資訊(矩陣0為memberNo,矩陣1為memberAcct)
            }
            else { // 如果為未登入狀態
                $("nav#tiny-menu > ul.user-menu").append(`<li id="logInAndLogOut"><a href="/BuyGo/front_end/pages/member/login.html">Log In</a></li>`); // 修改登入登出按鈕為Log In
            }
        });
    return response; // 將收到的會員詳細資訊回傳給呼叫方
}

function notLoggedInAction() {
    // 客戶未登入，執行頁面重定向到登入頁
    sessionStorage.setItem("redirectURL", (window.location.pathname + location.search)); // 保存當前網址至sessionStorage，以使登入後能返回該網址
    alert('帳號未登入，將導轉至登入頁面');
    window.location.href = '/BuyGo/front_end/pages/member/login.html'; // 導轉至登入頁面url
}