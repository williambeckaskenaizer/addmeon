//package com.comp350.william.addmeon;
//
//import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//import il.co.galex.battlenet.api.d3.model.account.User;
//import il.co.galex.battlenet.api.d3.model.common.BattleTag;
//import il.co.galex.battlenet.common.model.Region;
//import il.co.galex.battlenet.api.d3.network.CommunityOAuthProfileAPI;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static junit.framework.Assert.assertNotNull;
//import static junit.framework.Assert.assertTrue;
//
///**
// * Tests of requests to the D3 Community API
// * @author Alexander Gherschon
// */
//
//@RunWith(AndroidJUnit4.class)
//public class CommunityOAuthProfileApiTest {
//
//    private static final String ACCESS_TOKEN = "nestn9uhfgzpj953j3xpfpnw"; // problematic cause the code expires after 30 days
//
//    private static final BattleTag BATTLE_TAG = new BattleTag("Ahava", 2406);
//
//    @Test
//    public void getUserSync() throws Exception {
//
//        Context context = InstrumentationRegistry.getTargetContext();
//
//        User user = CommunityOAuthProfileAPI.getUser(context, Region.EU, ACCESS_TOKEN);
//        assertNotNull(user);
//        assertTrue(user.getBattleTag().equals(BATTLE_TAG));
//    }
//
//    @Test
//    public void getUserAsync() throws Exception {
//
//        final CountDownLatch lock = new CountDownLatch(1);
//        final Context context = InstrumentationRegistry.getTargetContext();
//
//        final User[] users = new User[1];
//
//        CommunityOAuthProfileAPI.getUser(context, Region.EU, ACCESS_TOKEN, new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//                users[0] = response.body();
//                lock.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//                lock.countDown();
//            }
//        });
//
//        lock.await(2000, TimeUnit.MILLISECONDS);
//
//        assertNotNull(users[0]);
//        assertTrue(BATTLE_TAG.equals(users[0].getBattleTag()));
//    }
//}
