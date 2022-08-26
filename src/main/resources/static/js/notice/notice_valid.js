function submit_valid(){
    let btn_submit = document.querySelector("#button-submit");
    btn_submit.addEventListener('click', ()=>{
        let target1 = document.querySelector('#notice-category');
        //let target2 = document.querySelector('#description');
        let target3 = document.querySelector('#youtubeUrl');
        let target4 = document.querySelector('#channelUrl');

        if (target1.value.length <= 0){
            alert('정확한 값을 입력하세요')
            return false
        }

        if (target3 != null && (target3.value.length <= 0 || !(target3.value.includes('youtube.com')))){
            alert('정확한 영상 주소를 입력하세요')
            return false
        }

        if (target4 != null && (target4.value.length <= 0 ||
            (!(target4.value.includes('youtube.com/c/')) && !(target4.value.includes('youtube.com/channel/')) && !(target4.value.includes('youtube.com/shorts/'))
            )
            )
        ){
            alert('정확한 채널 주소를 입력하세요')
            return false
        }

    })
}

