package quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type;

import android.util.Log;

import com.google.gson.Gson;
import com.horical.gito.chat.interactor.api.network.GsonUtils;
import com.horical.gito.chat.interactor.api.response.chat.MessageResponse;
import com.horical.gito.chat.interactor.api.response.chat.TypingResponse;
import com.horical.gito.chat.interactor.api.response.home.CreateConversationUserResponse;
import com.horical.gito.chat.interactor.api.response.home.StatusUserResponse;
import com.horical.gito.chat.interactor.api.response.setting.RoomInfoResponse;
import com.horical.gito.chat.model.ChatRoom;
import com.horical.gito.chat.model.Message;
import com.horical.gito.chat.model.RoomInfo;
import com.horical.gito.chat.model.Typing;

import org.json.JSONObject;


public class JsonObjectDataSocket {
    private static final String TAG = JsonObjectDataSocket.class.getSimpleName();
    private JSONObject jsonObject;
    private String keyId;

    public JsonObjectDataSocket(String keyId, Object... args) {
        this.jsonObject = (JSONObject) args[0];
        this.keyId = keyId;
    }

    public String getKeyId() {
        return keyId;
    }

    public CreateConversationUserResponse getConversationUser() {
        try {
            if (jsonObject != null)
                return new Gson().fromJson(jsonObject.toString(), CreateConversationUserResponse.class);
        } catch (Exception ignored) {
            Log.e(TAG, ignored.getMessage());
        }
        return null;

    }

    public Typing getTyping() {
        try {
            if (jsonObject != null) {
                TypingResponse typingResponse = new Gson().fromJson(jsonObject.toString(), TypingResponse.class);
                return typingResponse.getTyping();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public RoomInfo getDetailRoom() {
        try {
            if (jsonObject != null) {
                RoomInfoResponse roomInfo = new Gson().fromJson(jsonObject.toString(), RoomInfoResponse.class);
                return roomInfo.getRoomInfo();
            }
        } catch (Exception ignored) {
            Log.e(TAG, ignored.getMessage());
        }
        return null;
    }

    public Message getMessage() {
        try {
            if (jsonObject != null) {
                MessageResponse message = GsonUtils.createGson(ChatRoom.class).fromJson(jsonObject.toString(), MessageResponse.class);
                return message.getMessage();
            }
        } catch (Exception ignored) {
            Log.e(TAG, ignored.getMessage());
        }
        return null;
    }

    public StatusUserResponse.StatusUser getStatusUser() {
        try {
            if (jsonObject != null) {
                StatusUserResponse response = new Gson().fromJson(jsonObject.toString(), StatusUserResponse.class);
                return response.getStatusUser();
            }
        } catch (Exception ignored) {
            Log.e(TAG, ignored.getMessage());
        }
        return null;
    }
}
