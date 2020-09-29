package vip.qsos.form.mock

import vip.qsos.utils_net.lib.mock.AbstractMockData
import vip.qsos.utils_net.lib.mock.MockData

/**$
 * @author : 华清松
 */
class FormMockData : AbstractMockData() {

    override val config: MockData = MockData(
            "form", "GET", "/api/form/demo", "demo.json", 100L
    )

}