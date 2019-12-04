import random
from flask import Blueprint, render_template, request, jsonify, request
from db_api import read_all_features, find_report, find_photo, find_userinfo_by_id, search_by_tag
from mongoDatabase import db
from bson.json_util import dumps

search_api = Blueprint('search_api', __name__)

# [START gae_python37_datastore_render_user_times]
@search_api.route('/search', methods=['GET', 'POST'])
@search_api.route('/json/search', methods=['GET', 'POST'])
def searchReports():
    # Verify Firebase auth.
    error_message = None
    claims = None

    selected_tag = request.args.get('tag')

    #selected_feature = 'Board Walk Trail'

    mongo_reports = search_by_tag(db, tag=selected_tag)

    user_name = []
    report_img = []
    all_images = []
    all_tags = []
    theme_image_android = []
    for r in mongo_reports:
        user_name.append(find_userinfo_by_id(db, r['user_id'])['name'])
        if 'photos' in r and len(r['photos']) > 0:
            img = find_photo(db, r['photos'])
            report_img.append(img)
            for j in r['photos']:
                theme_image_android.append(j)
            for i in img:
                all_images.append(i)
        if 'tags' in r and len(r['tags']) > 0:
            for i in r['tags']:
                if i not in all_tags:
                    all_tags.append(i)

    mongo_reports = search_by_tag(db, tag=selected_tag)

    if (request.path == '/search'):
        return dumps({'reports': mongo_reports, 'user_name': user_name, 'selected_tag':selected_tag, 'theme_image':theme_image_android})
        #return dumps({'reports': mongo_reports, 'user_name': user_name, 'theme_tags':all_tags, 
                    #'selected_feature':selected_feature, 'report_images': report_img, 'theme_image':all_images})
    else:
        return render_template(
            'single_theme.html',
            user_data=claims,
            error_message=error_message,
            reports=mongo_reports,
            user_name=user_name,
            report_images=report_img,
            theme_image=all_images,
            theme_tags=all_tags,
            selected_tag=selected_tag)
