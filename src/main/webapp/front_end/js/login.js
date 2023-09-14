async function checkLoginStatusShowMemberAcct() {
    const response = await fetch('/BuyGo/api/front_end/checkLoginStatus', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json;charset:utf-8' },
    })
        .then(resp => resp.json())
        .then(body => {
            const { memberNo, memberAcct, loginState } = body;
            $("div.actions").css("text-align", "right");
            $("div.actions").html(`
                <strong id="showMemberAcct" style="font-weight: bold; white-space:nowrap;"></strong>
                <nav id="tiny-menu" class="clearfix">
                    <ul class="user-menu">
                        <li><a href="/BuyGo/front_end/pages/member/myaccount.html">我的帳戶<img
                        src="https://drive.google.com/uc?id=1EkcTLzseH_atgAiJ78ymmJoyuVUasGb7"></a></li>
                    </ul>
                </nav>
                `);
            if (loginState) { // 如果已為登入狀態
                $("nav#tiny-menu > ul.user-menu").append(`<li id="logInAndLogOut"><a href="/BuyGo/api/front_end/logOut">登出<img
                src="https://drive.google.com/uc?id=1uViDGF7OBfvCpNZp8pEcKFm5CmtAE8Pg"></a></li>`); // 修改登入登出按鈕為Log Out
                $("strong#showMemberAcct").html(`您好, ${memberAcct}`); // 顯示登入者帳號資訊
                let memberDetail = [memberNo, memberAcct];
                return memberDetail; // 回傳會員詳細資訊(矩陣0為memberNo,矩陣1為memberAcct)
            }
            else { // 如果為未登入狀態
                $("nav#tiny-menu > ul.user-menu").append(`<li id="logInAndLogOut"><a href="/BuyGo/front_end/pages/member/login.html">登入<img
                src="https://drive.google.com/uc?id=1uViDGF7OBfvCpNZp8pEcKFm5CmtAE8Pg"></a></li>`); // 修改登入登出按鈕為Log In
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