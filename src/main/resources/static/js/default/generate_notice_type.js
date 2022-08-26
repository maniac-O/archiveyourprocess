function generateNoticeType(){
    let select = ''
    select += '<select id="notice-category" class="notice-form-items notice-buttons" name="noticeType"><option value="">카테고리선택</option>';
    for (let i = 0; i < noticeType_name.length; i++) {
        select += '<option value='+noticeType_name[i]+'>'+noticeType_label[i]+'</option>'
    }
    select += '</select>'

    return select
}