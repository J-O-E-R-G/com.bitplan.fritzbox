/**
 * Copyright (c) 2018 BITPlan GmbH
 *
 * http://www.bitplan.com
 *
 * This file is part of the Opensource project at:
 * https://github.com/BITPlan/com.bitplan.fritzbox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bitplan.fritzbox;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock a Fritzbox
 * 
 * @author wf
 *
 */
public class FritzboxMock {
  // prepare a LOGGER
  protected static Logger LOGGER = Logger.getLogger("com.bitplan.fritzbox");

  /**
   * get a mocked Fritzbox that behaves like a real fritzbox according to the
   * test by simply "replaying" the function results
   * from the real environment of the developer
   * 
   * @return a Fritzbox
   * @throws Exception
   */
  public static Fritzbox getFritzbox() throws Exception {
    LOGGER.log(Level.INFO, "Mocking Fritzbox");
    Fritzbox fritzbox = spy(new FritzboxImpl());
    when(fritzbox.getUrl()).thenReturn("https://mockmyfritzbox");
    when(fritzbox.getPassword()).thenReturn("mocked secret password");
    when(fritzbox.getUsername()).thenReturn("mockeduser");
    FritzBoxSessionImpl session = spy(new FritzBoxSessionImpl(fritzbox));
    // now we can mock the session login
    doReturn(session).when(fritzbox).login();
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>0000000000000000</SID><Challenge>3c36041f</Challenge><BlockTime>0</BlockTime><Rights></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>b4125bc782830e71</SID><Challenge>e8398dca</Challenge><BlockTime>0</BlockTime><Rights><Name>Dial</Name><Access>2</Access><Name>App</Name><Access>2</Access><Name>HomeAuto</Name><Access>2</Access><Name>BoxAdmin</Name><Access>2</Access><Name>Phone</Name><Access>2</Access></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","?username=mockeduser&response=3c36041f-deadbfa9c98c8bfac5d1a445240cb647");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>0000000000000000</SID><Challenge>896cf006</Challenge><BlockTime>0</BlockTime><Rights></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>922e18b0f103bef3</SID><Challenge>d91e52fe</Challenge><BlockTime>0</BlockTime><Rights><Name>Dial</Name><Access>2</Access><Name>App</Name><Access>2</Access><Name>HomeAuto</Name><Access>2</Access><Name>BoxAdmin</Name><Access>2</Access><Name>Phone</Name><Access>2</Access></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","?username=mockeduser&response=896cf006-25743dd7d7573619f19135c7d9cb90a7");
    doReturn("087610092895,087610033476,087610092893,087610420045,087610128900,087610420054,087610092898,116570102016,116570095795,116570077974").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchlist");
    doReturn("<devicelist version=\"1\"><device identifier=\"08761 0092895\" id=\"16\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Jupiter</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>14090</power><energy>4636567</energy></powermeter><temperature><celsius>235</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0033476\" id=\"17\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Aquarium</name><switch><state>0</state><mode>auto</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>1140453</energy></powermeter><temperature><celsius>295</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092893\" id=\"19\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Wohnzimmer</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>81648</energy></powermeter><temperature><celsius>285</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420045\" id=\"20\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Island</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>135680</power><energy>222232</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0128900\" id=\"22\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Venus</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>890184</energy></powermeter><temperature><celsius>310</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420054\" id=\"23\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Media</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>22740</power><energy>198190</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092898\" id=\"24\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Merkur</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>465305</energy></powermeter><temperature><celsius>305</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0863063\" id=\"25\" functionbitmask=\"1280\" fwversion=\"03.86\" manufacturer=\"AVM\" productname=\"FRITZ!DECT Repeater 100\"><present>1</present><name>Repeater</name><temperature><celsius>290</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0102016\" id=\"26\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>Printer</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>61900</power><energy>464702</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0095795\" id=\"27\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>0</present><name>capri</name><switch><state></state><mode></mode><lock></lock><devicelock></devicelock></switch><powermeter><power></power><energy></energy></powermeter><temperature><celsius></celsius><offset></offset></temperature></device><device identifier=\"11657 0077974\" id=\"28\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>garten</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>14202</energy></powermeter><temperature><celsius>335</celsius><offset>0</offset></temperature></device></devicelist>").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getdevicelistinfos");
    doReturn("Jupiter").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610092895");
    doReturn("Aquarium").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610033476");
    doReturn("Wohnzimmer").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610092893");
    doReturn("Island").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610420045");
    doReturn("Venus").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610128900");
    doReturn("Media").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610420054");
    doReturn("Merkur").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610092898");
    doReturn("Repeater").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610863063");
    doReturn("Printer").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=116570102016");
    doReturn("capri").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=116570095795");
    doReturn("garten").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=116570077974");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>0000000000000000</SID><Challenge>410c743c</Challenge><BlockTime>0</BlockTime><Rights></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>86cb08bb95a5a9a7</SID><Challenge>4019da62</Challenge><BlockTime>0</BlockTime><Rights><Name>Dial</Name><Access>2</Access><Name>App</Name><Access>2</Access><Name>HomeAuto</Name><Access>2</Access><Name>BoxAdmin</Name><Access>2</Access><Name>Phone</Name><Access>2</Access></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","?username=mockeduser&response=410c743c-31913bfeddabe69ef2e075c7c1c49877");
    doReturn("<devicelist version=\"1\"><device identifier=\"08761 0092895\" id=\"16\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Jupiter</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>14090</power><energy>4636567</energy></powermeter><temperature><celsius>235</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0033476\" id=\"17\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Aquarium</name><switch><state>0</state><mode>auto</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>1140453</energy></powermeter><temperature><celsius>295</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092893\" id=\"19\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Wohnzimmer</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>81648</energy></powermeter><temperature><celsius>285</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420045\" id=\"20\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Island</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>135680</power><energy>222232</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0128900\" id=\"22\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Venus</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>890184</energy></powermeter><temperature><celsius>310</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420054\" id=\"23\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Media</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>22740</power><energy>198190</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092898\" id=\"24\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Merkur</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>465305</energy></powermeter><temperature><celsius>305</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0863063\" id=\"25\" functionbitmask=\"1280\" fwversion=\"03.86\" manufacturer=\"AVM\" productname=\"FRITZ!DECT Repeater 100\"><present>1</present><name>Repeater</name><temperature><celsius>290</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0102016\" id=\"26\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>Printer</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>61900</power><energy>464702</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0095795\" id=\"27\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>0</present><name>capri</name><switch><state></state><mode></mode><lock></lock><devicelock></devicelock></switch><powermeter><power></power><energy></energy></powermeter><temperature><celsius></celsius><offset></offset></temperature></device><device identifier=\"11657 0077974\" id=\"28\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>garten</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>14202</energy></powermeter><temperature><celsius>335</celsius><offset>0</offset></temperature></device></devicelist>").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getdevicelistinfos");
    doReturn("087610092895,087610033476,087610092893,087610420045,087610128900,087610420054,087610092898,116570102016,116570095795,116570077974").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchlist");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>0000000000000000</SID><Challenge>f4ef4415</Challenge><BlockTime>0</BlockTime><Rights></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>3a29a79ace9d1aa6</SID><Challenge>2a488d08</Challenge><BlockTime>0</BlockTime><Rights><Name>Dial</Name><Access>2</Access><Name>App</Name><Access>2</Access><Name>HomeAuto</Name><Access>2</Access><Name>BoxAdmin</Name><Access>2</Access><Name>Phone</Name><Access>2</Access></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","?username=mockeduser&response=f4ef4415-0dbb5ce247ad580fcb5670c8ee706df4");
    doReturn("<devicelist version=\"1\"><device identifier=\"08761 0092895\" id=\"16\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Jupiter</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>14090</power><energy>4636567</energy></powermeter><temperature><celsius>235</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0033476\" id=\"17\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Aquarium</name><switch><state>0</state><mode>auto</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>1140453</energy></powermeter><temperature><celsius>295</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092893\" id=\"19\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Wohnzimmer</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>81648</energy></powermeter><temperature><celsius>285</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420045\" id=\"20\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Island</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>135680</power><energy>222232</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0128900\" id=\"22\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Venus</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>890184</energy></powermeter><temperature><celsius>310</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420054\" id=\"23\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Media</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>22740</power><energy>198190</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092898\" id=\"24\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Merkur</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>465305</energy></powermeter><temperature><celsius>305</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0863063\" id=\"25\" functionbitmask=\"1280\" fwversion=\"03.86\" manufacturer=\"AVM\" productname=\"FRITZ!DECT Repeater 100\"><present>1</present><name>Repeater</name><temperature><celsius>290</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0102016\" id=\"26\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>Printer</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>61900</power><energy>464702</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0095795\" id=\"27\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>0</present><name>capri</name><switch><state></state><mode></mode><lock></lock><devicelock></devicelock></switch><powermeter><power></power><energy></energy></powermeter><temperature><celsius></celsius><offset></offset></temperature></device><device identifier=\"11657 0077974\" id=\"28\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>garten</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>14202</energy></powermeter><temperature><celsius>335</celsius><offset>0</offset></temperature></device></devicelist>").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getdevicelistinfos");
    doReturn("087610092895,087610033476,087610092893,087610420045,087610128900,087610420054,087610092898,116570102016,116570095795,116570077974").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchlist");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>0000000000000000</SID><Challenge>d195eb40</Challenge><BlockTime>0</BlockTime><Rights></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","");
    doReturn("<?xml version=\"1.0\" encoding=\"utf-8\"?><SessionInfo><SID>2a71d1782b8c06b2</SID><Challenge>78d443ff</Challenge><BlockTime>0</BlockTime><Rights><Name>Dial</Name><Access>2</Access><Name>App</Name><Access>2</Access><Name>HomeAuto</Name><Access>2</Access><Name>BoxAdmin</Name><Access>2</Access><Name>Phone</Name><Access>2</Access></Rights></SessionInfo>").when(session).doGetResponse("/login_sid.lua","?username=mockeduser&response=d195eb40-1a4a87fd8d0fe231137b0039739ac822");
    doReturn("<devicelist version=\"1\"><device identifier=\"08761 0092895\" id=\"16\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Jupiter</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>14090</power><energy>4636567</energy></powermeter><temperature><celsius>235</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0033476\" id=\"17\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Aquarium</name><switch><state>0</state><mode>auto</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>1140453</energy></powermeter><temperature><celsius>295</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092893\" id=\"19\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Wohnzimmer</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>81648</energy></powermeter><temperature><celsius>285</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420045\" id=\"20\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Island</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>127170</power><energy>222236</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0128900\" id=\"22\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Venus</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>890184</energy></powermeter><temperature><celsius>310</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0420054\" id=\"23\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Media</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>22740</power><energy>198190</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0092898\" id=\"24\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 200\"><present>1</present><name>Merkur</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>465305</energy></powermeter><temperature><celsius>305</celsius><offset>0</offset></temperature></device><device identifier=\"08761 0863063\" id=\"25\" functionbitmask=\"1280\" fwversion=\"03.86\" manufacturer=\"AVM\" productname=\"FRITZ!DECT Repeater 100\"><present>1</present><name>Repeater</name><temperature><celsius>290</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0102016\" id=\"26\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>Printer</name><switch><state>1</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>61900</power><energy>464702</energy></powermeter><temperature><celsius>265</celsius><offset>0</offset></temperature></device><device identifier=\"11657 0095795\" id=\"27\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>0</present><name>capri</name><switch><state></state><mode></mode><lock></lock><devicelock></devicelock></switch><powermeter><power></power><energy></energy></powermeter><temperature><celsius></celsius><offset></offset></temperature></device><device identifier=\"11657 0077974\" id=\"28\" functionbitmask=\"2944\" fwversion=\"03.87\" manufacturer=\"AVM\" productname=\"FRITZ!DECT 210\"><present>1</present><name>garten</name><switch><state>0</state><mode>manuell</mode><lock>0</lock><devicelock>0</devicelock></switch><powermeter><power>0</power><energy>14202</energy></powermeter><temperature><celsius>335</celsius><offset>0</offset></temperature></device></devicelist>").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getdevicelistinfos");
    doReturn("087610092895,087610033476,087610092893,087610420045,087610128900,087610420054,087610092898,116570102016,116570095795,116570077974").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchlist");
    doReturn("Media").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchname&ain=087610420054");
    doReturn("1").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchpresent&ain=087610420054");
    doReturn("1").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchstate&ain=087610420054");
    doReturn("22740").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchpower&ain=087610420054");
    doReturn("198190").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=getswitchenergy&ain=087610420054");
    doReturn("265").when(session).doGetResponse("/webservices/homeautoswitch.lua","?switchcmd=gettemperature&ain=087610420054");

    // CallList
    doReturn("sep=;\n" + 
        "Typ;Datum;Name;Rufnummer;Nebenstelle;Eigene Rufnummer;Dauer\n" + 
        "1;03.08.18 20:23;Reiner Notbogen;02154999999;My AB;Internet: 999999;0:01\n" + 
        "4;03.08.18 12:37;;02154999999;999 Kitchen;Internet: 999999;0:02\n").when(session).doGetResponse("/fon_num/foncalls_list.lua","?csv=");
    return fritzbox;
  }

}
