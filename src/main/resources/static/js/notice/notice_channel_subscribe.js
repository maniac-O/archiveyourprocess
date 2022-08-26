function onChannelSubscribeClick(e){
    let target_index = parseInt(e.getAttribute('id').substr(5));
    let target_class = e.classList
    if (target_class[target_class.length - 1] === 'sub-off')
        // 구독하는 로직
        ajax_on(e, target_index);
    else if (target_class[target_class.length - 1] === 'sub-on')
        ajax_off(e, target_index);
}

function ajax_off(e, index){

    $.ajax({
        url: "/notice/channel/subscribe/off",
        data: {'index':index},
        type: "POST",
        success : function(data){
            if(data === true){
                e.classList.remove('axi-turned-in')
                e.classList.add('axi-turned-in-not')
                e.classList.remove('sub-on')
                e.classList.add('sub-off')
            }

        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    });
}

function ajax_on(e, index){

    $.ajax({
        url: "/notice/channel/subscribe/on",
        data: {'index':index},
        type: "POST",
        success : function(data){
            console.log('data = ' +data)
            if(data === true){
                e.classList.remove('axi-turned-in-not')
                e.classList.add('axi-turned-in')
                e.classList.remove('sub-off')
                e.classList.add('sub-on')
            }

        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    });
}