import React from "react";
import style from "@/styles/ProductInfoDetailComponent.module.scss";

const ProductDetailComponent = ({ idx, product, type }) => {
  console.log(idx);
  console.log(product);
  console.log(type);
  return (
    <>
      {
        //cpu
        type == 0 ? (
          <div>
            <table className={style["product-detail-table"]}>
              <tbody>
                <tr>
                  <td className={style.title}>제조회사</td>
                  <td className={style.desc}>
                    {product?.corp != "0" ? product?.corp : null}
                  </td>
                  <td className={style.title}>등록년월</td>
                  <td className={style.desc}>
                    {product?.registeredAt != "0"
                      ? product?.registeredAt
                      : null}
                  </td>
                </tr>
                <tr>
                  {product?.intelCpu != "0" ? (
                    <>
                      <td className={style.title}>인텔 CPU 종류</td>
                      <td className={style.desc}>{product?.intelCpu}</td>
                    </>
                  ) : null}
                  {product?.amdCpu != "0" ? (
                    <>
                      <td className={style.title}>AMD CPU 종류</td>
                      <td className={style.desc}>{product?.amdCpu}</td>
                    </>
                  ) : null}
                  <td className={style.title}>소켓 구분</td>
                  <td className={style.desc}>
                    {product?.socket != "0" ? product?.socket : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>제조공정</td>
                  <td className={style.desc}>
                    {product?.nano != "0" ? product?.nano : null}
                  </td>
                </tr>
                <tr>
                  <td colSpan={4}>사양</td>
                </tr>
                <tr>
                  <td className={style.title}>코어수</td>
                  <td className={style.desc}>
                    {product?.core != "0" ? product?.core : null}
                  </td>
                  <td className={style.title}>쓰레드수</td>
                  <td className={style.desc}>
                    {product?.threadCnt != "0" ? product?.threadCnt : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>기본 클럭</td>
                  <td className={style.desc}>
                    {product?.clockOriginal != "0"
                      ? `${product?.clockOriginal} Ghz`
                      : null}
                  </td>
                  <td className={style.title}>최대 클럭</td>
                  <td className={style.desc}>
                    {product?.clockMax != "0"
                      ? `${product?.clockMax} Ghz`
                      : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>L3캐시</td>
                  <td className={style.desc}>
                    {product?.l3cash != "0" ? product?.l3cash : null}
                  </td>
                  <td className={style.title}>연산체계</td>
                  <td className={style.desc}>
                    {product?.arithmeticSystem != "0"
                      ? product?.arithmeticSystem
                      : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>버스속도</td>
                  <td className={style.desc}>
                    {product?.busSpeed != "0" ? product?.busSpeed : null}
                  </td>
                  <td className={style.title}>TDP</td>
                  <td className={style.desc}>
                    {product?.tdp != "0"
                      ? `${product?.tdp} ~ ${product?.tdpMax}W`
                      : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>PCIe 버전</td>
                  <td className={style.desc}>
                    {product?.pcieVersion != "0" ? product?.pcieVersion : null}
                  </td>
                  <td className={style.title}>최대 PCIe 레인수</td>
                  <td className={style.desc}>
                    {product?.pcieLane != "0" ? product?.pcieLane : null}
                  </td>
                </tr>
                <tr>
                  <td colSpan={4}>메모리 사양</td>
                </tr>
                <tr>
                  <td className={style.title}>최대 메모리 크기</td>
                  <td className={style.desc}>
                    {product?.memoryMaxSize != "0"
                      ? `${product?.memoryMaxSize}GB`
                      : null}
                  </td>
                  <td className={style.title}>메모리 규격</td>
                  <td className={style.desc}>
                    {product?.memoryFrame != "0" ? product?.memoryFrame : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>메모리 클럭</td>
                  <td className={style.desc}>
                    {product?.memoryClock != "0" ? product?.memoryClock : null}
                  </td>
                  <td className={style.title}>메모리 채널</td>
                  <td className={style.desc}>
                    {product?.memoryChannel != "0"
                      ? product?.memoryChannel == true
                        ? "2"
                        : "1"
                      : null}
                  </td>
                </tr>
                <tr>
                  <td colSpan={4}>그래픽 사양</td>
                </tr>
                <tr>
                  <td className={style.title}>내장 그래픽</td>
                  <td className={style.desc}>
                    {product?.includeVga != "0" ? product?.includeVga : null}
                  </td>
                  {product?.includeVga != "0" ? (
                    <>
                      <td className={style.title}>내장 그래픽 정보</td>
                      <td className={style.desc}>
                        {product?.includeVgaName != "0"
                          ? product?.includeVgaName
                          : null}
                      </td>
                    </>
                  ) : null}
                </tr>
                <tr>
                  <td colSpan={4}>기술 지원</td>
                </tr>
                <tr>
                  <td className={style.title}>하이퍼스레딩</td>
                  <td className={style.desc}>
                    {product?.hyperThreading == true ? "O" : "X"}
                  </td>
                  {product?.includeVga != "0" ? (
                    <>
                      <td className={style.title}>옵테인</td>
                      <td className={style.desc}>
                        {product?.optane == true ? "O" : "X"}
                      </td>
                    </>
                  ) : null}
                </tr>
                <tr>
                  <td colSpan={4}>구성</td>
                </tr>
                <tr>
                  <td className={style.title}>쿨러</td>
                  <td className={style.desc}>
                    {product?.includeCooler != "0"
                      ? product?.includeCooler
                      : null}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        ) : //mainboard
        type == 1 ? (
          <div>
            <table className={style["product-detail-table"]}>
              <tbody>
                <tr>
                  <td className={style.title}>제조회사</td>
                  <td className={style.desc}>
                    {product?.corp != "0" ? product?.corp : null}
                  </td>
                  <td className={style.title}>등록년월</td>
                  <td className={style.desc}>
                    {product?.registeredAt != "0"
                      ? product?.registeredAt
                      : null}
                  </td>
                </tr>
                <tr>
                  <td colSpan={4}>기본사양</td>
                </tr>
                <tr>
                  <td className={style.title}>제품 분류</td>
                  <td className={style.desc}>
                    {product?.amdApu ? "AMD CPU용" : "인텔 CPU용"}
                  </td>
                  <td className={style.title}>CPU 소켓</td>
                  <td className={style.desc}>
                    {product?.cpuSocket ? product?.cpuSocket : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>세부 칩셋</td>
                  <td className={style.desc}>
                    {product?.detailChipset ? product?.detailChipset : null}
                  </td>
                  <td className={style.title}>CPU 장착수</td>
                  <td className={style.desc}>
                    {product?.cpuCnt ? `${product?.cpuCnt}개` : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>폼팩터</td>
                  <td className={style.desc}>
                    {product?.formFactor ? product?.formFactor : null}
                  </td>
                </tr>
                <tr>
                  <td colSpan={4}>메모리</td>
                </tr>
                <tr>
                  <td className={style.title}>메모리 종류</td>
                  <td className={style.desc}>
                    {product?.memoryType ? product?.memoryType : null}
                  </td>
                  <td className={style.title}>메모리 속도</td>
                  <td className={style.desc}>
                    {product?.memorySpeed ? product?.memorySpeed : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>메모리 슬롯</td>
                  <td className={style.desc}>
                    {product?.memorySlot ? `${product?.memorySlot}개` : null}
                  </td>
                  <td className={style.title}>메모리 채널</td>
                  <td className={style.desc}>
                    {product?.memoryChannel
                      ? `${product?.memoryChannel}개`
                      : null}
                  </td>
                </tr>
                <tr>
                  <td className={style.title}>메모리 용량</td>
                  <td className={style.desc}>
                    {product?.maxMemoryVolume
                      ? `최대 ${product?.maxMemoryVolume}GB`
                      : null}
                  </td>
                  </tr>
                  <tr>
                    <td colSpan={4}>메모리 기술</td>
                  </tr>
                  <tr>
                  <td className={style.title}>XMP</td>
                  <td className={style.desc}>
                    {product?.xmp
                      ? `O`
                      : 'X'}
                    </td>
                    <td className={style.title}>옵테인</td>
                  <td className={style.desc}>
                    {product?.optane
                      ? `O`
                      : 'X'}
                  </td>
                  </tr>
                  <tr>
                    <td colSpan={4}>확장 슬롯</td>
                  </tr>
                  <tr>
                  <td className={style.title}>VGA 연결</td>
                  <td className={style.desc}>
                    {product?.vga != '0'
                      ? product?.vga
                      : null}
                    </td>
                  </tr>
                  <tr>
                    <td colSpan={4}>PCIe버전</td>
                  </tr>
                  <tr>
                  <td className={style.title}>PCIe4.0</td>
                  <td className={style.desc}>
                    {product?.pcie4
                      ? 'O'
                      : 'X'}
                    </td>
                  <td className={style.title}>PCIe3.0</td>
                  <td className={style.desc}>
                    {product?.pcie3
                      ? 'O'
                      : 'X'}
                    </td>
                  </tr>
                  <tr>
                  <td className={style.title}>PCIe</td>
                  <td className={style.desc}>
                    {product?.pcie
                      ? 'O'
                      : 'X'}
                    </td>
                  </tr>
                  <tr>
                    <td colSpan={4}>PCIe슬롯</td>
                  </tr>
                  <tr>
                  <td className={style.title}>PCIex16</td>
                  <td className={style.desc}>
                    {product?.pciex16 != '0'
                      ? `${product?.pciex16}개`
                      : null}
                    </td>
                  
                  <td className={style.title}>PCIex8</td>
                  <td className={style.desc}>
                    {product?.pciex8 != '0'
                      ? `${product?.pciex8}개`
                      : null}
                    </td>
                  </tr>
                  <tr>
                  <td className={style.title}>PCIex4</td>
                  <td className={style.desc}>
                    {product?.pciex4 != '0'
                      ? `${product?.pciex4}개`
                      : null}
                    </td>
                  <td className={style.title}>PCIex1</td>
                  <td className={style.desc}>
                    {product?.pciex1 != '0'
                      ? `${product?.pciex1}개`
                      : null}
                    </td>
                  </tr>
                  <tr>
                    <td colSpan={4}>
                      저장장치
                    </td>
                  </tr>
                  <tr>
                    <td className={style.title}>SATA3</td>
                    <td className={style.desc}>
                      {product?.sata3 != '0'
                        ? `${product?.sata3}개`
                        : null}
                    </td>
                    <td className={style.title}>M.2</td>
                    <td className={style.desc}>
                      {product?.sata3 != '0'
                        ? product?.m2
                        : null}
                    </td>
                  </tr>
              </tbody>
            </table>
          </div>
          ) : 
            //vga
            type == 2 ? 
              <div>
                <table className={style["product-detail-table"]}>
                  <tbody>
                  <tr>
                    <td className={style.title}>제조회사</td>
                    <td className={style.desc}>
                      {product?.corp != '0'
                        ? product?.corp
                        : null}
                    </td>
                    <td className={style.title}>등록년월</td>
                    <td className={style.desc}>
                      {product?.registeredAt != '0'
                        ? product?.registeredAt
                        : null}
                    </td>
                    </tr>
                    <tr>
                      <td colSpan={4}>칩셋 사양</td>
                    </tr>
                  <tr>
                    <td className={style.title}>칩셋 제조사</td>
                    <td className={style.desc}>
                      {product?.chipsetCorp != '0'
                        ? product?.chipsetCorp
                        : null}
                    </td>
                    <td className={style.title}>제품 시리즈</td>
                    <td className={style.desc}>
                      {product?.series != '0'
                        ? product?.series
                        : null}
                    </td>
                  </tr>
                  <tr>
                    <td className={style.title}>GPU 제조 공정</td>
                    <td className={style.desc}>
                      {product?.gpuProcess != '0'
                        ? product?.gpuProcess
                        : null}
                    </td>
                      {product?.nvidia != '0' ? 
                        <>
                        <td className={style.title}>NVIDIA 칩셋</td>
                        <td className={style.desc}>
                          {product?.nvidia != '0'
                            ? product?.nvidia
                            : null}
                        </td> 
                        </>
                        :
                        <>
                          <td className={style.title}>AMD 칩셋</td>
                        <td className={style.desc}>
                          {product?.amd != '0'
                            ? product?.amd
                            : null}
                        </td> 
                        </>

                      }
                    
                  </tr>
                  <tr>
                    <td className={style.title}>부스트클럭</td>
                    <td className={style.desc}>
                      {product?.boostClock != '0'
                        ? `${product?.boostClock}MHz`
                        : null}
                    </td>
                    <td className={style.title}>쿠다 프로세서</td>
                    <td className={style.desc}>
                      {product?.cudaProcessor != '0'
                        ? `${product?.cudaProcessor}개`
                        : null}
                    </td>
                  </tr>
                  <tr>
                    <td className={style.title}>인터페이스</td>
                    <td className={style.desc}>
                      {product?.interfaces != '0'
                        ? product?.interfaces 
                        : null}
                    </td>
                  </tr>
                    <tr>
                      <td colSpan={4}>메모리 사양</td>
                    </tr>
                    <tr>
                    <td className={style.title}>메모리 종류</td>
                    <td className={style.desc}>
                      {product?.memoryType != '0'
                        ? product?.memoryType 
                        : null}
                    </td>
                    <td className={style.title}>메모리 클럭</td>
                    <td className={style.desc}>
                      {product?.memoryClock != '0'
                        ? `${product?.memoryClock}MHz`
                        : null}
                    </td>
                  </tr>
                    <tr>
                    <td className={style.title}>메모리 용량</td>
                    <td className={style.desc}>
                      {product?.memoryVolume != '0'
                        ? `${product?.memoryVolume}GB`
                        : null}
                    </td>
                    <td className={style.title}>메모리 버스</td>
                    <td className={style.desc}>
                      {product?.memoryBus != '0'
                        ? `${product?.memoryBus}-bit`
                        : null}
                    </td>
                    </tr>
                    <tr>
                      <td colSpan={4}>그래픽 출력</td>
                    </tr>
                    <tr>
                    <td className={style.title}>HDMI</td>
                    <td className={style.desc}>
                      {product?.hdmi != '0'
                        ? `${product?.hdmi}개`
                        : null}
                    </td>
                    <td className={style.title}>DisplayPort</td>
                    <td className={style.desc}>
                      {product?.dp != '0'
                        ? `${product?.dp}개`
                        : null}
                    </td>
                    </tr>
                    <tr>
                    <td className={style.title}>모니터 지원</td>
                    <td className={style.desc}>
                      {product?.monitor != '0'
                        ? `최대 모니터 ${product?.monitor}개`
                        : null}
                      </td>
                    </tr>
                    <tr>
                      <td colSpan={4}>부가 기능</td>
                    </tr>
                    <tr>
                    <td className={style.title}>제로팬(0-db기술)</td>
                    <td className={style.desc}>
                      {product?.zeroFan
                        ? 'O'
                        : 'X'}
                      </td>
                    <td className={style.title}>8K 해상도 지원</td>
                    <td className={style.desc}>
                      {product?.display8k
                        ? 'O'
                        : 'X'}
                      </td>
                    </tr>
                    <tr>
                    <td className={style.title}>4K 해상도 지원</td>
                    <td className={style.desc}>
                      {product?.display4k
                        ? 'O'
                        : 'X'}
                      </td>
                    <td className={style.title}>HDR 지원</td>
                    <td className={style.desc}>
                      {product?.hdr
                        ? 'O'
                        : 'X'}
                      </td>
                    </tr>
                    <tr>
                    <td className={style.title}>HDCP 2.3</td>
                    <td className={style.desc}>
                      {product?.hdcp23
                        ? 'O'
                        : 'X'}
                    </td>
                    </tr>
                    <tr>
                      <td colSpan={4}>전력 관련</td>
                    </tr>
                    <tr>
                    <td className={style.title}>사용 전력</td>
                    <td className={style.desc}>
                      {product?.power != '0'
                        ? `최대${product?.power}W`
                        : null}
                    </td>
                    <td className={style.title}>권장 파워용량</td>
                    <td className={style.desc}>
                      {product?.recomPower != '0'
                        ? `정격파워 ${product?.recomPower}W 이상`
                        : null}
                    </td>
                    </tr>
                    <tr>
                      <td colSpan={4}>제품 외형</td>
                    </tr>
                    <tr>
                    <td className={style.title}>팬 개수</td>
                    <td className={style.desc}>
                      {product?.fanCnt != '0'
                        ? `${product?.fanCnt}개`
                        : null}
                    </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              :
              //ram
              type == 3 ?
                <div>
                  <table className={style["product-detail-table"]}>
                    <tbody>
                      <tr>
                        <td className={style.title}>제조회사</td>
                        <td className={style.desc}>
                          {product?.corp != '0'
                            ? product?.corp
                            : null}
                        </td>
                        <td className={style.title}>등록년월</td>
                        <td className={style.desc}>
                          {product?.registeredAt != '0'
                            ? product?.registeredAt
                            : null}
                        </td>
                      </tr>
                      <tr>
                        <td className={style.title}>사용장치</td>
                        <td className={style.desc}>
                          {product?.useDevice != '0'
                            ? product?.useDevice
                            : null}
                        </td>
                        <td className={style.title}>제품 분류</td>
                        <td className={style.desc}>
                          {product?.type != '0'
                            ? product?.type
                            : null}
                        </td>
                      </tr>
                      <tr>
                        <td className={style.title}>메모리 규격</td>
                        <td className={style.desc}>
                          {product?.memorySize != '0'
                            ? product?.memorySize
                            : null}
                        </td>
                        <td className={style.title}>메모리 용량</td>
                        <td className={style.desc}>
                          {product?.memoryVolume != '0'
                            ? `${product?.memoryVolume}GB`
                            : null}
                        </td>
                      </tr>
                      <tr>
                        <td className={style.title}>동작클럭(대역폭)</td>
                        <td className={style.desc}>
                          {product?.actionClock != '0'
                            ? `${product?.actionClock}MHZ`
                            : null}
                        </td>
                        <td className={style.title}>램개수</td>
                        <td className={style.desc}>
                          {product?.lamCnt != '0'
                            ? `${product?.lamCnt}개`
                            : null}
                        </td>
                      </tr>
                      <tr>
                        <td className={style.title}>히트싱크</td>
                        <td className={style.desc}>
                          {product?.heatsink != '0'
                            ? product?.heatsink
                            : null}
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                : type == 4 ?
                  <div>
                    <table className={style["product-detail-table"]}>
                      <tbody>
                        <tr>
                          <td className={style.title}>제조회사</td>
                          <td className={style.desc}>
                            {product?.corp != '0'
                              ? product?.corp
                              : null}
                          </td>
                          <td className={style.title}>등록년월</td>
                          <td className={style.desc}>
                            {product?.registeredAt != '0'
                              ? product?.registeredAt
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>제품 분류</td>
                          <td className={style.desc}>
                            {product?.type != '0'
                              ? product?.type
                              : null}
                          </td>
                          <td className={style.title}>정격출력</td>
                          <td className={style.desc}>
                            {product?.ratedPower != '0'
                              ? `${product?.ratedPower}W`
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>80 PLUS 인증</td>
                          <td className={style.desc}>
                            {product?.plusCert != '0'
                              ? product?.plusCert
                              : null}
                          </td>
                          <td className={style.title}>ETA인증</td>
                          <td className={style.desc}>
                            {product?.etaCert != '0'
                              ? product?.etaCert
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>LAMBDA인증</td>
                          <td className={style.desc}>
                            {product?.lambdaCert != '0'
                              ? product?.lambdaCert
                              : null}
                          </td>
                          <td className={style.title}>+12V 출력방식</td>
                          <td className={style.desc}>
                            {product?.outputMeth12v != '0'
                              ? product?.outputMeth12v
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>+12V 가용률</td>
                          <td className={style.desc}>
                            {product?.avail12v != '0'
                              ? `${product?.avail12v}%`
                              : null}
                          </td>
                          <td className={style.title}>쿨링팬 크기</td>
                          <td className={style.desc}>
                            {product?.coolingFanSize != '0'
                              ? `${product?.coolingFanSize}mm`
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>쿨링팬 개수</td>
                          <td className={style.desc}>
                            {product?.coolingFanCnt != '0'
                              ? product?.coolingFanCnt
                              : null}
                          </td>
                          <td className={style.title}>베어링</td>
                          <td className={style.desc}>
                            {product?.bearing != '0'
                              ? product?.bearing
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>A/S 보증기간</td>
                          <td className={style.desc}>
                            {product?.asPeriod != '0'
                              ? product?.asPeriod
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td colSpan={4}>DC 출력</td>
                        </tr>
                        <tr>
                          <td className={style.title}>+3.3V 출력</td>
                          <td className={style.desc}>
                            {product?.output3v != '0'
                              ? product?.output3v
                              : null}
                          </td>
                          <td className={style.title}>+5v 출력</td>
                          <td className={style.desc}>
                            {product?.output5v != '0'
                              ? `${product?.output5v}A`
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>+12V 출력</td>
                          <td className={style.desc}>
                            {product?.output12v != '0'
                              ? product?.output12v
                              : null}
                          </td>
                          <td className={style.title}>-12v 출력</td>
                          <td className={style.desc}>
                            {product?.outputMinus12v != '0'
                              ? product?.outputMinus12v
                              : null}
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>+5Vsb 출력</td>
                          <td className={style.desc}>
                            {product?.output5vsb != '0'
                              ? product?.output5vsb
                              : null}
                          </td>
                        </tr>

                        <tr>
                          <td colSpan={4}>
                            커넥터
                          </td>
                        </tr>
                        <tr>
                          <td className={style.title}>케이블연결</td>
                          <td className={style.desc}>
                            {product?.cableConn != '0'
                              ? product?.cableConn
                              : null}
                          </td>
                          <td className={style.title}>메인전원</td>
                          <td className={style.desc}>
                            {product?.mainPower != '0'
                              ? product?.mainPower
                              : null}
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  :
                  type == 5 ?
                    <div>
                      <table className={style["product-detail-table"]}>
                        <tbody>
                          <tr>
                            <td className={style.title}>제조회사</td>
                            <td className={style.desc}>
                              {product?.corp != '0'
                                ? product?.corp
                                : null}
                            </td>
                            <td className={style.title}>등록년월</td>
                            <td className={style.desc}>
                              {product?.registeredAt != '0'
                                ? product?.registeredAt
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td colSpan={4}>기본사양</td>
                          </tr>
                          <tr>
                            <td className={style.title}>제품분류</td>
                            <td className={style.desc}>
                              {product?.type != '0'
                                ? product?.type
                                : null}
                            </td>
                            <td className={style.title}>폼팩터</td>
                            <td className={style.desc}>
                              {product?.formFactor != '0'
                                ? product?.formFactor
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>인터페이스</td>
                            <td className={style.desc}>
                              {product?.interfaces != '0'
                                ? product?.interfaces
                                : null}
                            </td>
                            <td className={style.title}>용량</td>
                            <td className={style.desc}>
                              {product?.volume != '0'
                                ? `${product?.volume}GB`
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>메모리 타입</td>
                            <td className={style.desc}>
                              {product?.memoryType != '0'
                                ? product?.memoryType
                                : null}
                            </td>
                            <td className={style.title}>낸드 구조</td>
                            <td className={style.desc}>
                              {product?.nandStructure != '0'
                                ? product?.nandStructure
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>RAM 유무</td>
                            <td className={style.desc}>
                              {product?.ram != '0'
                                ? product?.ram
                                : null}
                            </td>
                            <td className={style.title}>RAM 타입</td>
                            <td className={style.desc}>
                              {product?.ramType != '0'
                                ? product?.ramType
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>컨트롤러</td>
                            <td className={style.desc}>
                              {product?.controller != '0'
                                ? product?.controller
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td colSpan={4}>
                               성능
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>순차읽기</td>
                            <td className={style.desc}>
                              {product?.seqRead != '0'
                                ? `${product?.seqRead}MB/S`
                                : null}
                            </td>
                            <td className={style.title}>순차쓰기</td>
                            <td className={style.desc}>
                              {product?.seqWrite != '0'
                                ? `${product?.seqWrite}MB/S`
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>읽기IOPS</td>
                            <td className={style.desc}>
                              {product?.readIops != '0'
                                ? `${product?.readIops}K`
                                : null}
                            </td>
                            <td className={style.title}>쓰기IOPS</td>
                            <td className={style.desc}>
                              {product?.writeIops != '0'
                                ? `${product?.writeIops}K`
                                : null}
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                    :
                    type == 6 ?
                      <div>
                        <table className={style["product-detail-table"]}>
                          <tbody>
                          <tr>
                            <td className={style.title}>제조회사</td>
                            <td className={style.desc}>
                              {product?.corp != '0'
                                ? product?.corp
                                : null}
                            </td>
                            <td className={style.title}>등록년월</td>
                            <td className={style.desc}>
                              {product?.registeredAt != '0'
                                ? product?.registeredAt
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>제품 분류</td>
                            <td className={style.desc}>
                              {product?.type != '0'
                                ? product?.type
                                : null}
                            </td>
                            <td className={style.title}>디스크 크기</td>
                            <td className={style.desc}>
                              {product?.diskSize != '0'
                                ? product?.diskSize
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>디스크 용량</td>
                            <td className={style.desc}>
                              {product?.diskVolume != '0'
                                ? `${product?.diskVolume > 1024 ? `${product?.diskVolume/1024}TB` : `${product?.diskVolume}GB`}`
                                : null}
                            </td>
                            <td className={style.title}>인터페이스</td>
                            <td className={style.desc}>
                              {product?.interfaces != '0'
                                ? product?.interfaces
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>회전수</td>
                            <td className={style.desc}>
                              {product?.rotate != '0'
                                ? `${product?.rotate}RPM`
                                : null}
                            </td>
                            <td className={style.title}>버퍼 용량</td>
                            <td className={style.desc}>
                              {product?.bufferSize != '0'
                                ? `${product?.bufferSize}MB`
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>전송 속도</td>
                            <td className={style.desc}>
                              {product?.transSpeed != '0'
                                ? `최대 ${product?.transSpeed}MB/S`
                                : null}
                            </td>
                            <td className={style.title}>기록방식</td>
                            <td className={style.desc}>
                              {product?.recordMethod != '0'
                                ? product?.recordMethod
                                : null}
                            </td>
                          </tr>
                          <tr>
                            <td className={style.title}>디스크 수</td>
                            <td className={style.desc}>
                              {product?.diskCnt != '0'
                                ? `${product?.diskCnt}개`
                                : null}
                            </td>
                            <td className={style.title}>두께</td>
                            <td className={style.desc}>
                              {product?.thickness != '0'
                                ? `${product?.thickness}mm`
                                : null}
                            </td>
                          </tr>
                          </tbody>
                        </table>
                      </div>
                      : type == 7 ?
                        <div>
                          <table className={style["product-detail-table"]}>
                            <tbody>
                              <tr>
                                <td className={style.title}>제조회사</td>
                                <td className={style.desc}>
                                  {product?.corp != '0'
                                    ? product?.corp
                                    : null}
                                </td>
                                <td className={style.title}>등록년월</td>
                                <td className={style.desc}>
                                  {product?.registeredAt != '0'
                                    ? product?.registeredAt
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>제품 분류</td>
                                <td className={style.desc}>
                                  {product?.classType != '0'
                                    ? product?.classType
                                    : null}
                                </td>
                                <td className={style.title}>케이스 크기</td>
                                <td className={style.desc}>
                                  {product?.size != '0'
                                    ? product?.size
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>파워포함여부</td>
                                <td className={style.desc}>
                                  {product?.power != '0'
                                    ? product?.power
                                    : null}
                                </td>
                                <td className={style.title}>지원파워규격</td>
                                <td className={style.desc}>
                                  {product?.powerSize != '0'
                                    ? product?.powerSize
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td colSpan={4}>지원보드규격</td>
                              </tr>
                              <tr>
                                <td className={style.title}>Extended-ATX</td>
                                <td className={style.desc}>
                                  {product?.extendedAtx
                                    ? 'O'
                                    : 'X'}
                                </td>
                                <td className={style.title}>표준-ATX</td>
                                <td className={style.desc}>
                                  {product?.standardAtx
                                    ? 'O'
                                    : 'X'}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>Micro-ATX</td>
                                <td className={style.desc}>
                                  {product?.microAtx
                                    ? 'O'
                                    : 'X'}
                                </td>
                                <td className={style.title}>Mini-ITX</td>
                                <td className={style.desc}>
                                  {product?.miniItx
                                    ? 'O'
                                    : 'X'}
                                </td>
                              </tr>
                              <tr>
                                <td colSpan={4}>내부 확장</td>
                              </tr>
                              <tr>
                                <td className={style.title}>8.9cm베이</td>
                                <td className={style.desc}>
                                  {product?.bay8
                                    ? `${product?.bay8}개`
                                    : null}
                                </td>
                                <td className={style.title}>6.4cm베이</td>
                                <td className={style.desc}>
                                  {product?.bay6
                                    ? `${product?.bay6}개`
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>저장장치 장착</td>
                                <td className={style.desc}>
                                  {product?.storageDevice
                                    ? `${product?.storageDevice}개`
                                    : null}
                                </td>
                                <td className={style.title}>PCI 슬롯</td>
                                <td className={style.desc}>
                                  {product?.pciSlot
                                    ? product?.pciSlot
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td colSpan={4}>쿨러 / 튜닝</td>
                              </tr>
                              <tr>
                                <td className={style.title}>쿨링팬</td>
                                <td className={style.desc}>
                                  {product?.coolingFan
                                    ? `총${product?.coolingFan}개`
                                    : null}
                                </td>
                                <td className={style.title}>LED팬</td>
                                <td className={style.desc}>
                                  {product?.ledFan
                                    ? `${product?.ledFan}개`
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>측면</td>
                                <td className={style.desc}>
                                  {product?.side
                                    ? product?.side
                                    : null}
                                </td>
                                <td className={style.title}>후면</td>
                                <td className={style.desc}>
                                  {product?.back
                                    ? product?.back
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>전면</td>
                                <td className={style.desc}>
                                  {product?.front
                                    ? product?.front
                                    : null}
                                </td>
                                <td className={style.title}>먼지필터</td>
                                <td className={style.desc}>
                                  {product?.filter
                                    ? product?.filter
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td colSpan={4}>외부포트</td>
                              </tr>
                              <tr>
                                <td className={style.title}>USB</td>
                                <td className={style.desc}>
                                  {product?.usb
                                    ? 'O'
                                    : 'X'}
                                </td>
                                <td className={style.title}>USB 3.0</td>
                                <td className={style.desc}>
                                  {product?.usb3
                                    ? 'O'
                                    : 'X'}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>USB 3.1 (Type-C)</td>
                                <td className={style.desc}>
                                  {product?.typec31
                                    ? 'O'
                                    : 'X'}
                                </td>
                              </tr>
                              <tr>
                                <td colSpan={4}>크기</td>
                              </tr>
                              <tr>
                                <td className={style.title}>너비(W)</td>
                                <td className={style.desc}>
                                  {product?.width != '0'
                                    ? `${product?.width}mm`
                                    : null}
                                </td>
                                <td className={style.title}>깊이(D)</td>
                                <td className={style.desc}>
                                  {product?.deepth != '0'
                                    ? `${product?.deepth}mm`
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>높이(H)</td>
                                <td className={style.desc}>
                                  {product?.height != '0'
                                    ? `${product?.height}mm`
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td colSpan={4}>호환성</td>
                              </tr>
                              <tr>
                                <td className={style.title}>파워 장착</td>
                                <td className={style.desc}>
                                  {product?.powerMounting != '0'
                                    ? `${product?.powerMounting}mm`
                                    : null}
                                </td>
                                <td className={style.title}>파워 위치</td>
                                <td className={style.desc}>
                                  {product?.powerLocation != '0'
                                    ? product?.powerLocation
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>GPU 장착</td>
                                <td className={style.desc}>
                                  {product?.gpuMounting != '0'
                                    ? `${product?.gpuMounting}mm`
                                    : null}
                                </td>
                                <td className={style.title}>CPU쿨러 장착</td>
                                <td className={style.desc}>
                                  {product?.cpuCoolerMounting != '0'
                                    ? `${product?.cpuCoolerMounting}mm`
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>수랭쿨러 규격</td>
                                <td className={style.desc}>
                                  {product?.waterCoolerSize != '0'
                                    ? `최대 ${product?.waterCoolerSize}열 지원`
                                    : null}
                                </td>
                                <td className={style.title}>라디에이터(상단)</td>
                                <td className={style.desc}>
                                  {product?.radiatorTop != '0'
                                    ? product?.radiatorTop
                                    : null}
                                </td>
                              </tr>
                              <tr>
                                <td className={style.title}>라디에이터(젼면)</td>
                                <td className={style.desc}>
                                  {product?.radiatorFront != '0'
                                    ? product?.radiatorFront
                                    : null}
                                </td>
                                <td className={style.title}>라디에이터(후면)</td>
                                <td className={style.desc}>
                                  {product?.radiatorBack != '0'
                                    ? product?.radiatorBack
                                    : null}
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                        : type == 8 ?
                          <div>
                            <table className={style["product-detail-table"]}>
                              <tbody>
                                <tr>
                                  <td className={style.title}>제조회사</td>
                                  <td className={style.desc}>
                                    {product?.corp != '0'
                                      ? product?.corp
                                      : null}
                                  </td>
                                  <td className={style.title}>등록년월</td>
                                  <td className={style.desc}>
                                    {product?.registeredAt != '0'
                                      ? product?.registeredAt
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>냉각방식</td>
                                  <td className={style.desc}>
                                    {product?.coolingSystem != '0'
                                      ? product?.coolingSystem
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td colSpan={4}>인텔 소켓</td>
                                </tr>
                                <tr>
                                  <td className={style.title}>LGA1700</td>
                                  <td className={style.desc}>
                                    {product?.lga1700
                                      ? 'O'
                                      : 'X'}
                                  </td>
                                  <td className={style.title}>LGA1200</td>
                                  <td className={style.desc}>
                                    {product?.lga1200
                                      ? 'O'
                                      : 'X'}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>LGA115X</td>
                                  <td className={style.desc}>
                                    {product?.lga115x
                                      ? 'O'
                                      : 'X'}
                                  </td>
                                </tr>
                                <tr>
                                  <td colSpan={4}>AMD 소켓</td>
                                </tr>
                                <tr>
                                  <td className={style.title}>AM4</td>
                                  <td className={style.desc}>
                                    {product?.am4
                                      ? 'O'
                                      : 'X'}
                                  </td>
                                </tr>
                                <tr>
                                  <td colSpan={4}>상세 정보</td>
                                </tr>
                                <tr>
                                  <td className={style.title}>TDP</td>
                                  <td className={style.desc}>
                                    {product?.tdp != '0'
                                      ? `${product?.tdp}W`
                                      : null}
                                  </td>
                                  <td className={style.title}>재질</td>
                                  <td className={style.desc}>
                                    {product?.texture != '0'
                                      ? product?.texture
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>히트파이프</td>
                                  <td className={style.desc}>
                                    {product?.heatpipe != '0'
                                      ? product?.heatpipe
                                      : null}
                                  </td>
                                  <td className={style.title}>쿨러높이</td>
                                  <td className={style.desc}>
                                    {product?.coolerHeight != '0'
                                      ? `${product?.coolerHeight}mm`
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>무게</td>
                                  <td className={style.desc}>
                                    {product?.weight != '0'
                                      ? product?.weight
                                      : null}
                                  </td>
                                  <td className={style.title}>팬 크기</td>
                                  <td className={style.desc}>
                                    {product?.fanSize != '0'
                                      ? `${product?.fanSize}mm`
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>팬 두께</td>
                                  <td className={style.desc}>
                                    {product?.fanThickness != '0'
                                      ? product?.fanThickness
                                      : null}
                                  </td>
                                  <td className={style.title}>커넥터</td>
                                  <td className={style.desc}>
                                    {product?.connector != '0'
                                      ? product?.connector
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>베어링</td>
                                  <td className={style.desc}>
                                    {product?.bearing != '0'
                                      ? product?.bearing
                                      : null}
                                  </td>
                                  <td className={style.title}>최대 팬속도</td>
                                  <td className={style.desc}>
                                    {product?.fanSpeedMax != '0'
                                      ? `${product?.fanSpeedMax}RPM`
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>최대 풍량</td>
                                  <td className={style.desc}>
                                    {product?.windVolumeMax != '0'
                                      ? product?.windVolumeMax
                                      : null}
                                  </td>
                                  <td className={style.title}>최대 풍압</td>
                                  <td className={style.desc}>
                                    {product?.windPressureMax != '0'
                                      ? product?.windPressureMax
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td className={style.title}>최대 소음</td>
                                  <td className={style.desc}>
                                    {product?.noiseMax != '0'
                                      ? `최대 ${product?.noiseMax}dBA`
                                      : null}
                                  </td>
                                </tr>
                                <tr>
                                  <td colSpan={4}>부가 기능</td>
                                </tr>
                                <tr>
                                  <td className={style.title}>PWM 지원</td>
                                  <td className={style.desc}>
                                    {product?.pwm
                                      ? 'O'
                                      : 'X'}
                                  </td>
                                  <td className={style.title}>A/S 기간</td>
                                  <td className={style.desc}>
                                    {product?.asPeriod != '0'
                                      ? product?.asPeriod
                                      : null}
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                          :
                          null
      }
    </>
  );
};

export default ProductDetailComponent;
