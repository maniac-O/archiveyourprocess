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
        url: "/notice/channel/more",
        data: {'index':index},
        type: "GET",
        success : function(data){
            if (data.length > 0){
                create_component(data)
                e.classList.remove('last'+index)
                e.classList.add('last'+data[data.length-1].id)
                console.log(data)
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

        let channel = "\'channel\'"

        if (login !== 'none'){
            if(data[i].subscribe === true){
                icon = '<div class="card-item-upper-right">' +
                    '   <i class="axi axi-turned-in subscribe sub-on" onclick="onChannelSubscribeClick(this)" id="index'+data[i].id+'"></i>\n'

            }
            else if(data[i].subscribe === null || data[i].subscribe === false){
                icon = '<div class="card-item-upper-right">' +
                    '<i class="axi axi-turned-in-not subscribe sub-off" onclick="onChannelSubscribeClick(this)" id="index'+data[i].id+'"></i>\n'
            }

            if (data[i].member.email === login)
                icon += '<i class="axi axi-more-vert notice-setting" onclick="showSettings(this, '+channel+')" id="setting'+data[i].id+'"></i>'

            icon += '</div>';
        }


        console.log(data[i].id)
        target.innerHTML +=
            '<div class="card-item '+ theme +'" id="card'+data[i].id+'">\n' +
            '            <div class="card-item-upper">\n' +
            '                <div class="card-item-upper-left">\n' +
            '                    <span class="card-item-channel">'+ data[i].channelName +'</span>\n' +
            '                </div>\n' +
                        icon +
            '            </div>\n' +
            '           <div class="card-item-middle">\n' +
            '                <div class="card-item-middle-img">' +
            '                   <a href='+ data[i].channelUrl+' target=\'_blank\'>\n'+
            '                       <div class="card-item-middle-img">\n' +
            '                          <img class="card-item-img card-item-middle-thumbnail" src='+data[i].thumbnail+'>\n' +
            '                       </div>' +
            '                    </a>' +
            '               </div>' +
            '            </div>' +
            '            <!--iframe class="youtube-item" th:src="*{channel.getChannelUrl()}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe-->\n' +
            '\n' +
            '            <div class="card-item-under">\n' +
            '                <a href="/channel/name?id='+data[i].member.id+'">' +
            '                   <span class="card-item-displayName" >'+data[i].member.displayName+'</span>\n' +
            '                </a>' +
            '                <a href="/channel/type?noticeType='+data[i].noticeType+'">' +
            '                   <span class="card-item-noticeType noticeType_'+data[i].noticeType+'">'+label+'</span>\n' +
            '                </a>' +
            '            </div>\n' +
            '            <div class="card-item-description">'+data[i].description+'</div>\n' +
            '            <div class="card-item-time">'+data[i].time.lastTime.substr(0, 10)+'</div>' +
            '        </div>'




    }



}