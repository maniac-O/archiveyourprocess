let video_text = ''
let channel_text = ''

initHTML()
initNoticeWriteForm()
submit_valid()

function initHTML(){
    let select = generateNoticeType();
    video_text = ' <form id="notice-form" action="/notice/write" method="post">' +
        '            <input class="notice-form-items notice-write-item-item" id="description" type="text" name="description" placeholder="짧은 메모" value="">' +
        '            <input class="notice-form-items notice-write-item-item" id="youtubeUrl" type="text" name="youtubeUrl" placeholder="유튜브 영상 URL" value="">' +
        '            <div id="wrap-whos-youtube">' +
        '               <span>채널 주인장 : </span>' +
        '               <span id="whos-youtube"></span>' +
        '            </div>' +
        select +
        '            <input class="notice-form-items notice-write-item-item notice-buttons" id="button-submit" type="submit" value="전송">' +
        '        </form>'

    channel_text = ' <form id="notice-form" action="/notice/write" method="post">' +
        '            <input class="notice-form-items notice-write-item-item" id="description" type="text" name="description" placeholder="짧은 메모"/>' +
        '            <input class="notice-form-items notice-write-item-item" id="channelUrl" type="text" name="channelUrl" placeholder="유튜브 채널 URL"/>' +
        '            <div id="wrap-whos-youtube">' +
        '               <span>채널 주인장 : </span>' +
        '               <span id="whos-youtube"></span>' +
        '            </div>' +
        select +
        '            <input class="notice-form-items notice-write-item-item notice-buttons" id="button-submit" type="submit" value="전송">' +
        '        </form>'
}

function initNoticeWriteForm(){
    // form 생성과 이벤트 할당
    let target = document.querySelector('#wrap-notice-form');
    target.innerHTML = video_text
    addYoutubeEvent()

    // 비디오 or 채널 선택 버튼
    let notice_type = document.querySelector('#notice-type');
    notice_type.addEventListener('change', (e)=>{
        let target = document.querySelector('#wrap-notice-form');

        if( e.target.value === 'channel'){

            target.innerHTML = channel_text
            addChannelEvent()

        }else if( e.target.value === 'video'){

            target.innerHTML = video_text
            addYoutubeEvent()
        }
        submit_valid()
        recallTheme()
    })
}
