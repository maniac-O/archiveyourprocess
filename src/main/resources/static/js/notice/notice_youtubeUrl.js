function addYoutubeEvent(){
    let youtubeUrl = document.querySelector('#youtubeUrl');

    youtubeUrl.addEventListener('change', (e)=>{
        console.log(e.target.value.length)

        if ( e.target.value.length <= 0 )
            return

        if (e.target.value.includes('youtube.com/shorts/'))
            ajax('shorts', e.target.value)
        else
            ajax('video', e.target.value)
    })
}

function addChannelEvent(){
    let channelUrl = document.querySelector('#channelUrl');


    channelUrl.addEventListener('change', (e)=>{
        console.log(e.target.value.length)

        if ( e.target.value.length <= 0 )
            return

        ajax('channel', e.target.value)
    })
}


function ajax(type, url){
    if (!(url.includes('youtube.com')))
        return

    $.ajax({
        url: "/notice/write-api",
        data: {'url':url, 'type': type},
        type: "GET",
        success : function(data){
            if(data.length !== 2)
                return

            let whos_youtube = document.querySelector('#whos-youtube');
            whos_youtube.innerText = data[1]

            console.log('success')
            console.log('>>> '+data)
        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    });
}
