/**
 * Created by falco on 23-10-15.
 */

function getObjects(element, resource, callback) {
    $.getJSON(resource, function (data) {

        $.each(data, function (i, objects) {
            for (object in objects) {
                callback(element, objects[object]);
            }
        });
    });
    return;
}
