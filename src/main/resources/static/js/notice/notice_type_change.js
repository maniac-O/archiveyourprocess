let notice_type_change = document.querySelector('#notice-type-change');

notice_type_change.addEventListener('change',()=>{
    window.location.href="/notice/type?noticeType="+notice_type_change.value;
})