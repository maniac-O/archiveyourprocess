function onShowMoreClick(e){
    let target_classes = e.getAttribute('class').split(' ');
    let last_index = '';

    for (let i = 0; i < target_classes.length; i++) {
        if (target_classes[i].includes('last'))
            last_index = target_classes[i].substr(4);

    }

    ajax_show_more(e, last_index)
}

function ajax_show_more(e, index){

    $.ajax({
        url: "/notice/video/more",
        data: {'index':index},
        type: "GET",
        success : function(data){
            console.log(data)
            if (data.length > 0){
                create_component(data)
                e.classList.remove('last'+index)
                e.classList.add('last'+data[data.length-1].id)
            }

        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    });
}

function create_component(data){
    let theme = document.querySelector('body').classList;

    let target = document.querySelector('#cards');
    for (let i = 0; i < data.length; i++) {

        let label = noticeType_label[noticeType_name.indexOf(data[i].noticeType)];

        let icon = ''
        let login = document.querySelector('.login-valid').getAttribute('id');

        let video = "\'video\'"

        if (login !== 'none'){
            if(data[i].subscribe === true){
                icon = '<div class="card-item-upper-right">' +
                        '   <i class="axi axi-turned-in subscribe sub-on" onclick="onSubscribeClick(this)" id="index'+data[i].id+'"></i>\n'

            }
            else if(data[i].subscribe === null || data[i].subscribe === false){
                icon = '<div class="card-item-upper-right">' +
                    '<i class="axi axi-turned-in-not subscribe sub-off" onclick="onSubscribeClick(this)" id="index'+data[i].id+'"></i>\n'
            }

            if (data[i].member.email === login)
                icon += '<i class="axi axi-more-vert notice-setting" onclick="showSettings(this, '+video+')" id="setting'+data[i].id+'"></i>'

            icon += '</div>';
        }




        target.innerHTML +=
            '<div class="card-item '+ theme +'" id="card'+data[i].id+'">\n' +
            '            <div class="card-item-upper">\n' +
            '                <div class="card-item-upper-left">\n' +
            '                    <img class="card-item-img" src='+ data[i].thumbnail +'>\n' +
            '                    <span class="card-item-channel" th:text="*{notice.getChannelName()}"></span>\n' +
            '                </div>\n' +
            // 여기 if else로 처리
                            icon +
            '            </div>\n' +
            '            <iframe class="youtube-item" src='+ data[i].youtubeUrl +' title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>\n' +
            '\n' +
            '            <div class="card-item-under">\n' +
            '                <a href="/notice/name?id='+data[i].member.id+'">' +
            '                   <span class="card-item-displayName">'+ data[i].member.displayName +'</span>\n' +
            '                </a>' +
            '                <a href="/notice/type?noticeType='+data[i].noticeType+'">' +
            '                   <span class="card-item-noticeType noticeType_'+data[i].noticeType+'" >'+ label +'</span>\n' +
            '                </a>' +
            '            </div>\n' +
            '            <div class="card-item-description" th:text="*{notice.getDescription()}">'+data[i].description+'</div>\n' +
            '            <div class="card-item-time">'+data[i].time.lastTime.substr(0,10)+'</div>' +
            '        </div>'

    }



}